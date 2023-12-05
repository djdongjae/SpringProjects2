package com.groom.cookiehousedemo.service.auth;

import com.groom.cookiehousedemo.authentication.oauth2.userInfo.OAuth2UserInfo;
import com.groom.cookiehousedemo.config.jwt.JwtService;
import com.groom.cookiehousedemo.controller.response.auth.SignInResponseDto;
import com.groom.cookiehousedemo.domain.user.SocialType;
import com.groom.cookiehousedemo.domain.user.User;
import com.groom.cookiehousedemo.exception.Error;
import com.groom.cookiehousedemo.exception.model.NotFoundException;
import com.groom.cookiehousedemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final Long TOKEN_EXPIRATION_TIME_ACCESS = 100 * 24 * 60 * 60 * 1000L; // 100일
    private final Long TOKEN_EXPIRATION_TIME_REFRESH = 200 * 24 * 60 * 60 * 1000L; // 200일

    private final UserRepository userRepository;

    @Transactional
    public SignInResponseDto signIn(OAuth2UserInfo oAuth2UserInfo, String provider) {
        SocialType socialType = SocialType.valueOf(provider.toUpperCase());
        String socialId = oAuth2UserInfo.getId();
        String userName = oAuth2UserInfo.getName();

        Boolean isRegistered = userRepository.existsBySocialIdAndSocialType(socialId, socialType);

        if (!isRegistered) {
            User newUser = User.builder()
                    .userName(userName)
                    .socialId(socialId)
                    .socialType(socialType)
                    .build();
            userRepository.save(newUser);
        }

        User user = userRepository.findBySocialIdAndSocialType(socialId, socialType)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        String accessToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_ACCESS);
        String refreshToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_REFRESH);

        user.updateRefreshToken(refreshToken);
        return SignInResponseDto.of(user.getId(), user.getUserName(), accessToken, refreshToken, isRegistered);
    }

}
