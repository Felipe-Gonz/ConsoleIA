package com.luisgonzalez.ConsoleIA.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

@Component
public class FileManagerUtil {

    public Path createTempDirectory() throws IOException{
        return Files.createTempDirectory("java-playground-");
    }

    public Path writeJavaFile(Path tempDir, String code) throws IOException{
        Path javaFile = tempDir.resolve("Main.java");
        Files.writeString(javaFile, code, StandardCharsets.UTF_8);
        return javaFile;
    }

    public void deleteDirectory(Path directory){
        try{
            Files.walk(directory).sorted(Comparator.reverseOrder()).forEach(path ->{
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e){
                    System.err.println("No se pudo eliminar " + path);
                }
            });
        } catch (IOException e){
            System.err.println("Error al limpiar el directorio temporal " + directory);
        }
    }
}
