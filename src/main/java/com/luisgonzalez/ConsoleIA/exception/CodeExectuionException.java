package com.luisgonzalez.ConsoleIA.exception;

public class CodeExectuionException extends RuntimeException{

    public CodeExectuionException(String message){
        super(message);
    }

    public CodeExectuionException(String message, Throwable cause){
        super(message, cause);
    }
}
