package com.luisgonzalez.ConsoleIA.util;

import com.luisgonzalez.ConsoleIA.dto.ProcessExecutionResult;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Component
public class ProcessExecutorUtil {

    private static final long COMPILE_TIMEOUT_SECONDS = 5;
    private static final long RUN_TIMEOUT_SECONDS = 3;

    public ProcessExecutionResult compileJavaFile(Path tempDir) throws Exception{
        ProcessBuilder compilerBuilder = new ProcessBuilder("javac", "Main.java");
        compilerBuilder.directory(tempDir.toFile());

        Process compileProcess = compilerBuilder.start();

        boolean finished = compileProcess.waitFor(COMPILE_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        if (!finished){
            compileProcess.destroyForcibly();
            return new ProcessExecutionResult("", "Compilation time limit exceeded", -1, "COMPILATION_TIMEOUT");
        }

        String output = readStream(compileProcess.getInputStream());
        String error = readStream(compileProcess.getErrorStream());
        int exitCode = compileProcess.exitValue();

        return new ProcessExecutionResult(output, error, exitCode, exitCode == 0 ? "SUCESS" : "COMPILATION_ERROR");
    }

    public ProcessExecutionResult runJavaFile(Path tempDir, String input) throws Exception{
        ProcessBuilder runBuilder = new ProcessBuilder("java", "Main.java");
        runBuilder.directory(tempDir.toFile());

        Process runProcess = runBuilder.start();

        if (input != null && !input.trim().isEmpty()){
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(runProcess.getOutputStream(), StandardCharsets.UTF_8))) {
                writer.write(input);
                writer.newLine();
                writer.flush();
            }
        } else {
            runProcess.getOutputStream().close();
        }

        boolean finished = runProcess.waitFor(RUN_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        if (!finished){
            runProcess.destroyForcibly();
            return new ProcessExecutionResult("", "Excetuion time limit exceeded", -1, "TIMEOUT");
        }

        String output = readStream(runProcess.getInputStream());
        String error = readStream(runProcess.getErrorStream());
        int exitCode = runProcess.exitValue();

        return new ProcessExecutionResult(output, error, exitCode, exitCode == 0 ? "SUCESS" : "RUNTIME_ERROR");
    }

    private String readStream(InputStream inputStream) throws IOException {
        StringBuilder content  = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null){
                content.append(line).append(System.lineSeparator());
            }
            return content.toString();
        }
    }

}
