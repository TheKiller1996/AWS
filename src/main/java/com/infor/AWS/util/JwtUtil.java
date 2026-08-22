package com.infor.AWS.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private final SecretKey signingKey;

    @Value("${app.jwt.expiration-minutes:60}")
    private  long expirationMinute;

    public JwtUtil(@Value("${app.jwt.secret}") String secret){
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UUID userid, String email){
        Date now = new Date();
        Date expiry = new Date(now.getTime() + (expirationMinute * 60*1000));

        return Jwts.builder().setSubject(userid.toString())
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(this.signingKey, SignatureAlgorithm.HS256).compact();
    }

    public UUID validateAndGetUserId(String token){
        Claims claims = Jwts.parser().
                setSigningKey(this.signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return UUID.fromString(claims.getSubject());
    }
}
