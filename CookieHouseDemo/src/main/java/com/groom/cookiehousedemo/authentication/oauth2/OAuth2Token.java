package com.groom.cookiehousedemo.authentication.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2Token {

    private String token;
    private String refreshToken;
    private LocalDateTime expiredAt;

}
