package com.groom.cookiehousedemo.exception.model;

import com.groom.cookiehousedemo.exception.Error;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(Error error, String message) {
        super(error, message);
    }
}
