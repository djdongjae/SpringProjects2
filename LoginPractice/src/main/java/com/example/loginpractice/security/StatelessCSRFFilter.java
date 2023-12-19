package com.example.loginpractice.security;

import com.example.loginpractice.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
public class StatelessCSRFFilter extends OncePerRequestFilter {

    // CSRF 토큰 상수 정의
    public static final String CSRF_TOKEN = "CSRF-TOKEN";
    public static final String X_CSRF_TOKEN = "X-CSRF-TOKEN";

    // CSRF 보호가 필요한지 여부를 확인하는 Matcher 및 액세스 거부 핸들러 정의
    private final RequestMatcher requireCsrfProtectionMatcher = new DefaultRequiresCsrfMatcher();
    private final AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // CSRF 보호가 필요한지 확인
        if (requireCsrfProtectionMatcher.matches(request)) {
            // HTTP 헤더 및 쿠키에서 CSRF 토큰 가져오기
            Optional<String> optCsrfToken = Optional.ofNullable(request.getHeader(X_CSRF_TOKEN));
            Optional<Cookie> optCsrfCookie = CookieUtils.getCookie(request, CSRF_TOKEN);

            log.debug(optCsrfCookie.isPresent() ? optCsrfToken.get() : "CSRF 토큰 헤더가 존재하지 않습니다.");
            log.debug(optCsrfCookie.isPresent() ? optCsrfCookie.get().getValue() : "CSRF 쿠키가 존재하지 않습니다.");

            // CSRF 토큰 비교 및 일치하지 않으면 액세스 거부 핸들러 호출
            if (!optCsrfCookie.isPresent() || !optCsrfCookie.isPresent() || !optCsrfToken.get().equals(optCsrfCookie.get().getValue())) {
                accessDeniedHandler.handle(request, response, new AccessDeniedException("CSRF 토큰이 유효하지 않습니다."));
                return;
            }
        }
        // CSRF 보호가 필요하지 않거나, 토큰이 유효한 경우 요청 계속 진행
        filterChain.doFilter(request, response);
    }

    public static final class DefaultRequiresCsrfMatcher implements RequestMatcher {
        // CSRF 보호가 필요하지 않은 HTTP 메서드 정의
        private final Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)");

        @Override
        public boolean matches(HttpServletRequest request) {
            // CSRF 보호가 필요한지 여부 반환
            return !allowedMethods.matcher(request.getMethod()).matches();
        }
    }
}
