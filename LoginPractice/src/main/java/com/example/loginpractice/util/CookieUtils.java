package com.example.loginpractice.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CookieUtils {

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        addCookie(response, name, value, false, false, maxAge);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, boolean httpOnly, boolean secure, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/"); // 전체 애플리케이션에서 쿠키 사용 가능
        cookie.setHttpOnly(httpOnly); // JavaScript에서 쿠키에 접근하는 것을 설정
        cookie.setSecure(secure); // HTTPS에서만 전송 가능하게 할 지 설정
        cookie.setMaxAge(maxAge); //  쿠키의 만료 시간을 설정
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

}
