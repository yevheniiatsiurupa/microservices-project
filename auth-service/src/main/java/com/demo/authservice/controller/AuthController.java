package com.demo.authservice.controller;

import com.demo.authservice.entity.User;
import com.demo.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Value("${app.home-url}")
    private String homeUrl;

    private final UserService userService;

    @GetMapping("/custom-login")
    public String customLoginPage() {
        return "login";
    }

    @PostMapping("/signup")
    public String signupOrLogin(@RequestBody User user) {
        String token = userService.signUpOrLogin(user);
        String redirectUrl = homeUrl + "?auth_token=" + token;
        log.debug("REDIRECT URL " + redirectUrl);
        return "redirect:" + redirectUrl;
    }

}
