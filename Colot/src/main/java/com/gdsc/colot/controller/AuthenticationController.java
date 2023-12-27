package com.gdsc.colot.controller;

import com.gdsc.colot.common.dto.BaseResponse;
import com.gdsc.colot.controller.dto.request.AuthorizationRequestDto;
import com.gdsc.colot.controller.dto.request.OAuth2AuthorizationRequestDto;
import com.gdsc.colot.controller.dto.response.OAuth2AuthorizationResponseDto;
import com.gdsc.colot.controller.dto.response.SignInResponseDto;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.SuccessCode;
import com.gdsc.colot.exception.model.OAuth2RequestFailedException;
import com.gdsc.colot.jwt.JwtProvider;
import com.gdsc.colot.oauth2.ClientRegistration;
import com.gdsc.colot.oauth2.ClientRegistrationRepository;
import com.gdsc.colot.oauth2.InMemoryOAuth2RequestRepository;
import com.gdsc.colot.oauth2.OAuth2Token;
import com.gdsc.colot.oauth2.userInfo.OAuth2UserInfo;
import com.gdsc.colot.service.oauth2.OAuth2Service;
import com.gdsc.colot.service.oauth2.OAuth2ServiceFactory;
import com.gdsc.colot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final InMemoryOAuth2RequestRepository inMemoryOAuth2RequestRepository;
    private final JwtProvider jwtProvider;
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/authorize")
    public BaseResponse<SignInResponseDto> authenticateUsernamePassword(@Valid @RequestBody AuthorizationRequestDto authorizationRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationRequestDto.getUsername(), authorizationRequestDto.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final SignInResponseDto data = SignInResponseDto.of(jwtProvider.generateToken(userDetails.getUsername()));
        return BaseResponse.success(SuccessCode.LOGIN_SUCCESS, data);
    }

    @GetMapping("/oauth2/authorize/{provider}")
    public void redirectSocialAuthorizationPage(@PathVariable String provider, HttpServletRequest request, HttpServletResponse response) throws  Exception {
        String state = generateState();

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        OAuth2Service oAuth2Service = OAuth2ServiceFactory.getOAuth2Service(restTemplate, provider);
        oAuth2Service.redirectAuthorizePage(clientRegistration, state, response);
    }

    @RequestMapping("/oauth2/callback/{provider}")
    public BaseResponse<SignInResponseDto> oAuth2AuthenticationCallback(@PathVariable String provider, OAuth2AuthorizationResponseDto oAuth2AuthorizationResponseDto, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (oAuth2AuthorizationResponseDto.getError() != null) {
            ErrorCode errorCode = ErrorCode.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(String.format("%s [응답 코드: %s]", oAuth2AuthorizationResponseDto.getError(), oAuth2AuthorizationResponseDto.getCode()))
                    .build();
            throw new OAuth2RequestFailedException(errorCode, errorCode.getMessage());
        }

        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(provider);
        OAuth2Service oAuth2Service = OAuth2ServiceFactory.getOAuth2Service(restTemplate, provider);

        OAuth2Token oAuth2Token = oAuth2Service.getAccessToken(clientRegistration, oAuth2AuthorizationResponseDto.getCode(), oAuth2AuthorizationResponseDto.getState());
        OAuth2UserInfo oAuth2UserInfo = oAuth2Service.getUserInfo(clientRegistration, oAuth2Token.getToken());

        UserDetails userDetails = userService.login
    }

    private String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}
