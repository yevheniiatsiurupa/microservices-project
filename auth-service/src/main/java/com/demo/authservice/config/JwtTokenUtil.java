package com.demo.authservice.config;

import com.demo.authservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "microservices-123-key";

    public static String generateToken(User user) {
        log.debug("Inside JwtTokenUtil");

        Claims claims = Jwts.claims().setSubject(user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("https://devglan.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

}