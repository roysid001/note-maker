package com.sid.notemaker.response;

public class ApiError {
    private final int status;
    private final String error;
    private final String message;

    public ApiError(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public String getError() {
        return error;
    }
}
