package com.luisgonzalez.ConsoleIA.dto;

public class RunCodeResponse {

    private String output;
    private String error;
    private String status;
    private Integer exitCode;

    public RunCodeResponse() {
    }

    public RunCodeResponse(String output, String error, String status, Integer exitCode) {
        this.output = output;
        this.error = error;
        this.status = status;
        this.exitCode = exitCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getExitCode() {
        return exitCode;
    }

    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }
}
