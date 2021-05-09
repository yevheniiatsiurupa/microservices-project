package com.demo.authservice.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleOAuth2UserInfo {

    private String id;

    private String name;

    private String email;

    private String imageUrl;

}
