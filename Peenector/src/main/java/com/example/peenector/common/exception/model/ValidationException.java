package com.example.peenector.common.exception.model;

import com.example.peenector.common.exception.ErrorCode;

public class ValidationException extends PeenectorException {

    public ValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ValidationException(String message) {
        super(message, ErrorCode.VALIDATION_EXCEPTION);
    }

}
