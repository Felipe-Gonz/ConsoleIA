package com.luisgonzalez.ConsoleIA.model;

public class CompilationResult {
    private boolean success;
    private String error;
    private int exitCode;
    private long executionTimeMs;

    public CompilationResult() {
    }

    public CompilationResult(boolean success, String error, int exitCode, long executionTimeMs) {
        this.success = success;
        this.error = error;
        this.exitCode = exitCode;
        this.executionTimeMs = executionTimeMs;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }
}
