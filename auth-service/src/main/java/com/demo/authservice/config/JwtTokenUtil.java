package com.demo.authservice.config;

import com.demo.authservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "microservices-123-key";

    public String generateToken(User user, List<String> authorities) {
        log.debug("Inside JwtTokenUtil");

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("authorities", authorities);

        return Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(user.getEmail())
                .setIssuer("http://microservices-app.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

}