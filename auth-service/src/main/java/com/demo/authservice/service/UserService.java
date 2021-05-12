package com.demo.authservice.service;

import com.demo.authservice.config.JwtTokenUtil;
import com.demo.authservice.entity.User;
import com.demo.authservice.entity.UserType;
import com.demo.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public String signUpOrLogin(User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser != null) {
            String storedPassword = dbUser.getPassword();
            if (bcryptEncoder.matches(user.getPassword(), storedPassword)) {
                user.setPassword(bcryptEncoder.encode(user.getPassword()));
                return jwtTokenUtil.generateToken(user);
            } else {
                throw new RuntimeException("Wrong password.");
            }
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setUserType(UserType.SIMPLE);
        userRepository.save(user);
        return jwtTokenUtil.generateToken(user);
    }
}
