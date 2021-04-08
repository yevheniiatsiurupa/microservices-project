package com.demo.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class InteractionConfig {

    @Bean(name = "httpRestTemplate")
    public RestTemplate httpRestTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
}
