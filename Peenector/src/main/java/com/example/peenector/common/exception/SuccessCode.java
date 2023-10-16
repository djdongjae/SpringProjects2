package com.example.peenector.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.example.peenector.common.exception.StatusCode.SUCCESS;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    SIGNUP_SUCCESS(SUCCESS, "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(SUCCESS, "로그인이 완료되었습니다."),

    GET_STUDENT_SUCCESS(SUCCESS, "회원 조회가 완료되었습니다."),
    GET_TEAM_SUCCESS(SUCCESS, "팀 조회가 완료되었습니다."),

    GET_MISSION_SUCCESS(SUCCESS, "미션 조회가 완료되었습니다."),
    ;

    private final StatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
