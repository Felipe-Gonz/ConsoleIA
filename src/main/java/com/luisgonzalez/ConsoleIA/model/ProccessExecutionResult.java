package com.luisgonzalez.ConsoleIA.model;

public class ProccessExecutionResult {
    private String sdtout;
    private String stderr;
    private int exitCode;
    private long executionTimeMs;
    private boolean timeOut;

    public ProccessExecutionResult() {
    }

    public ProccessExecutionResult(String sdtout, String stderr, int exitCode, long executionTimeMs, boolean timeOut) {
        this.sdtout = sdtout;
        this.stderr = stderr;
        this.exitCode = exitCode;
        this.executionTimeMs = executionTimeMs;
        this.timeOut = timeOut;
    }

    public String getSdtout() {
        return sdtout;
    }

    public void setSdtout(String sdtout) {
        this.sdtout = sdtout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
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

    public boolean isTimeOut() {
        return timeOut;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }
}
