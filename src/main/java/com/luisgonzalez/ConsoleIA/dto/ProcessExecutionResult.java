package com.luisgonzalez.ConsoleIA.dto;

public class ProcessExecutionResult {
    private String output;
    private String error;
    private int exitCode;
    private String status;


    public ProcessExecutionResult(String output, String error, int exitCode, String status) {
        this.output = output;
        this.error = error;
        this.exitCode = exitCode;
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
