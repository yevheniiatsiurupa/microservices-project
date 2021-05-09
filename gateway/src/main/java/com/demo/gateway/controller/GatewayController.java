package com.demo.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

//    @GetMapping(value = "/start")
//    public ResponseEntity<String> testInteraction(@AuthenticationPrincipal OidcUser user,
//                                                  @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client,
//                                                  @RequestHeader("Authorization") String authHeader) {
//        String header = authHeader == null ? "No authorization header" : "Authorization header: " + authHeader;
//
//        String userInfo = "Username: " + user.getName() + ", user email: " + user.getEmail() + ", token: " + user.getIdToken().getTokenValue();
//        String clientInfo = " Client token: " + client.getAccessToken().getTokenValue();
//        return ResponseEntity.ok(header + userInfo + clientInfo);
//    }

    @GetMapping(value = "/home")
    public ResponseEntity<String> redirectLogin(@RequestParam(value = "auth_token") String token) {
        return ResponseEntity.ok("Token - " + token);
    }
}
