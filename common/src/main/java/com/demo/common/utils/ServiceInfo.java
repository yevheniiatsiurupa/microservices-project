package com.demo.common.utils;

import lombok.Getter;

@Getter
public enum ServiceInfo {

    COMMENT_SERVICE("comment-service", "/cs"),
    POST_SERVICE("post-service", "/ps");

    private final String id;
    private final String url;

    ServiceInfo(String id, String url) {
        this.id = id;
        this.url = url;
    }

}
