package com.demo.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class InteractionService {

    private final DiscoveryClient discoveryClient;

    public  <T> ResponseEntity<T> execute(ServiceInfo serviceInfo,
                                          String pathToConcreteEndpoint,
                                          HttpMethod method,
                                          HttpServletRequest request,
                                          Object body,
                                          Class<T> zClass) {
        String url = discoveryClient.getInstances(serviceInfo.getId()).get(0).getUri().toString()
                .concat(serviceInfo.getUrl())
                .concat(pathToConcreteEndpoint);

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        return restTemplate.exchange(url, method, new HttpEntity<>(body), zClass);
    }
}
