package com.demo.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationCustomFilter implements GatewayFilterFactory<AuthenticationCustomFilter.Config> {

    public static final String SIGNING_KEY = "microservices-123-key";

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(Claims claims) {
        final Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    @Override
    public Class<Config> getConfigClass() {
        return AuthenticationCustomFilter.Config.class;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.debug("Inside gateway filter");
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().toString();
            log.debug("Request uri: " + path);

            String token = request.getQueryParams().getFirst("auth_token");
            if (token == null || token.isEmpty()) {
                return getForbiddenResponse(exchange);
            }

            try {
                log.debug("Token: " + token);
                Claims claims = getAllClaimsFromToken(token);
                log.debug("JWT token is parsed successfully");

                if (isTokenExpired(claims)) {
                    log.debug("Token has expired");
                    return getForbiddenResponse(exchange);
                }

                String username = claims.getSubject();
                if (username != null) {
                    @SuppressWarnings("unchecked")
                    List<String> authorities = (List<String>) claims.get("authorities");
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                    log.debug("Authentication is set with values parsed from jwt token");
                }

            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
                SecurityContextHolder.clearContext();
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> getForbiddenResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    @Override
    public Config newConfig() {
        return new Config("AuthenticationCustomFilter");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config {
        private String name;
    }
}