package com.luisgonzalez.ConsoleIA.service;

import com.luisgonzalez.ConsoleIA.dto.RunCodeRequest;
import com.luisgonzalez.ConsoleIA.dto.RunCodeResponse;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

@Service
public class CodeExecutionService {

    private static final long COMPILE_TIMEOUT_SECONDS = 5;
    private static final long RUN_TIMEOUT_SECONDS = 3;

    public RunCodeResponse executeCode(RunCodeRequest runCodeRequest){
        Path tempDir = null;

        try{
            tempDir = Files.createTempDirectory("java-playground-");

            Path javaFile = tempDir.resolve("Main.java");
            Files.writeString(javaFile, runCodeRequest.getCode(), StandardCharsets.UTF_8);

            RunCodeResponse compileRepsonse = compileJavaFile(tempDir);
            if (!"SUCESS".equals(compileRepsonse.getStatus())) {
                return compileRepsonse;
            }

            return runJavaFile(tempDir, runCodeRequest.getInput());

        } catch (Exception e) {
            return new RunCodeResponse(null, e.getMessage(), "INTERNAL ERROR", -1);
        } finally {
            if (tempDir != null){
                deleteDirectory(tempDir);
            }
        }
    }

    private RunCodeResponse compileJavaFile(Path tempDir) throws Exception{
        ProcessBuilder compilerBuilder = new ProcessBuilder("javac", "Main.java");
        compilerBuilder.directory(tempDir.toFile());

        Process compileProcess = compilerBuilder.start();

        boolean finished = compileProcess.waitFor(COMPILE_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        if (!finished){
            compileProcess.destroyForcibly();
            return new RunCodeResponse("","Compilation time limit exceeded","COMPILATION_TIMEOUT", -1);
        }

        String compileOutput = readStream(compileProcess.getInputStream());
        String compileError = readStream(compileProcess.getErrorStream());
        int compileExitCode = compileProcess.exitValue();

        if(compileExitCode != 0){
            return new RunCodeResponse(compileOutput, compileError, "COMPILATION ERROR", compileExitCode);
        }

        return new RunCodeResponse(compileOutput, compileError, "SUCESS", compileExitCode);
    }

    private RunCodeResponse runJavaFile(Path tempDir, String input) throws Exception{
        ProcessBuilder runBuilder = new ProcessBuilder("java", "Main");
        runBuilder.directory(tempDir.toFile());

        Process runProcess = runBuilder.start();

        if (input != null && !input.trim().isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(runProcess.getOutputStream(), StandardCharsets.UTF_8))) {
                writer.write(input);
                writer.newLine();
                writer.flush();
            }
        } else {
            runProcess.getOutputStream().close();
        }

        boolean finished = runProcess.waitFor(RUN_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        if (!finished) {
            runProcess.destroyForcibly();
            return new RunCodeResponse(
                    "",
                    "Execution time limit exceeded",
                    "TIMEOUT",
                    -1
            );
        }

        String runOutput = readStream(runProcess.getInputStream());
        String runError = readStream(runProcess.getErrorStream());
        int runExitCode = runProcess.exitValue();

        return new RunCodeResponse(runOutput, runError, runExitCode == 0 ? "SUCESS":"RUNTIME ERROR", runExitCode);
    }

    private String readStream(InputStream inputStream) throws IOException{
        StringBuilder content = new StringBuilder();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        while ((line = reader.readLine()) != null){
                content.append(line).append(System.lineSeparator());
        }

        reader.close();
        return content.toString();
    }

    private void deleteDirectory(Path directory){
        try{
            Files.walk(directory).sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                }catch (IOException e){
                    System.err.println("No se pudo eliminar " + path);
                }
            });
        } catch (IOException e){
            System.err.println("Error al limpiar el directorio temporal " + directory);
        }
    }
}
