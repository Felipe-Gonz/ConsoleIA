package com.luisgonzalez.ConsoleIA.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorResponse {
    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;
    private List<String> details;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String message, String error, int status, LocalDateTime timestamp, List<String> details) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.timestamp = timestamp;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
