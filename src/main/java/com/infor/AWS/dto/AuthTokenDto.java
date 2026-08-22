package com.infor.AWS.dto;

import java.time.Instant;

public class AuthTokenDto {
    String token;
    Instant expirationAt;
    String refreshToken;
}
