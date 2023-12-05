package com.groom.cookiehousedemo.base.dto;

import com.groom.cookiehousedemo.exception.Error;
import com.groom.cookiehousedemo.exception.Success;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    private final int code;
    private final String message;
    private T data;

    public static BaseResponse success(Success success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    public static <T> BaseResponse<T> success(Success success, T data) {
        return new BaseResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    public static BaseResponse error(Error error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    public static BaseResponse error(Error error, String message) {
        return new BaseResponse<>(error.getHttpStatusCode(), message);
    }

}
