package com.gdsc.colot.controller;

import com.gdsc.colot.common.dto.BaseResponse;
import com.gdsc.colot.controller.dto.request.AuthorizationRequestDto;
import com.gdsc.colot.controller.dto.response.SignInResponseDto;
import com.gdsc.colot.exception.SuccessCode;
import com.gdsc.colot.jwt.JwtProvider;
import com.gdsc.colot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/authorize")
    public BaseResponse<SignInResponseDto> authenticateUsernamePassword(@Valid @RequestBody AuthorizationRequestDto authorizationRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequestDto.getUsername(), authorizationRequestDto.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final SignInResponseDto data = SignInResponseDto.of(jwtProvider.generateToken(userDetails.getUsername()));
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, data);
    }

}
