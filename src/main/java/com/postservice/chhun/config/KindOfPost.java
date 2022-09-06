package com.postservice.chhun.config;

public enum KindOfPost {
    KOR_POST("우체국", "KPost"),
    KOR_CJ("CJ", "CJPost");

    private String name;
    private String className;

    KindOfPost(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }
}
