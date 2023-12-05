package com.groom.cookiehousedemo.exception.model;

import com.groom.cookiehousedemo.exception.Error;

public class NotFoundException extends CustomException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
