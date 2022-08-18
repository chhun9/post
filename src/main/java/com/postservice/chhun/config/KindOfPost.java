package com.postservice.chhun.config;

public enum KindOfPost {
    K_POST("K-Post","kp"),
    CJ("Cj","cj");

    private String desc;
    private String code;

    KindOfPost(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

}
