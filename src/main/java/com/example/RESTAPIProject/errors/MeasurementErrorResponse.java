package com.example.RESTAPIProject.errors;

import java.time.LocalDateTime;

public class MeasurementErrorResponse {
    private String message;

    private LocalDateTime timestamp;

    public MeasurementErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
