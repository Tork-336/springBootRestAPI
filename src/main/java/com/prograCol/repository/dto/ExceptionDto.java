package com.prograCol.repository.dto;

import java.time.LocalDateTime;

public class ExceptionDto {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String description;

    public ExceptionDto() {
    }

    public ExceptionDto(LocalDateTime timestamp, int status, String message, String description) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
