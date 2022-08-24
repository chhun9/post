package com.postservice.chhun.config;

import java.util.HashMap;
import java.util.Map;

public enum KindOfPost {
    KOR_POST("우체국"),
    KOR_CJ("CJ");

    private String name;
    public static final Map<String, KindOfPost> map = new HashMap<>();

    KindOfPost(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
