package com.example.peenector.common.exception.model;

import com.example.peenector.common.exception.ErrorCode;

public class PeenectorException extends RuntimeException {
    private final ErrorCode errorCode;

    public PeenectorException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
