package com.luisgonzalez.ConsoleIA.dto.response;

import com.luisgonzalez.ConsoleIA.model.ExecutionStatus;

public class RunCodeResponse {

    private ExecutionStatus status;
    private String output;
    private String error;
    private Integer exitCode;
    private Long executionTimeMs;

    public RunCodeResponse() {
    }

    public RunCodeResponse(ExecutionStatus status, String output, String error, Integer exitCode, Long executionTimeMs) {
        this.status = status;
        this.output = output;
        this.error = error;
        this.exitCode = exitCode;
        this.executionTimeMs = executionTimeMs;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(ExecutionStatus status) {
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

    public Integer getExitCode() {
        return exitCode;
    }

    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }

    public Long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(Long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }
}
