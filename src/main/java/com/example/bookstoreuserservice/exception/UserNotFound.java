package com.example.bookstoreuserservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UserNotFound extends RuntimeException{
    private long errorCode;
    private String statusMessage;

    public UserNotFound(long errorCode, String statusMessage) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
    }
}
