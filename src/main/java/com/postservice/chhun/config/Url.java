package com.postservice.chhun.config;

public enum Url {
    KOR_POST_URL("https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm"),
    KOR_CJ_POST_FIRST_URL("https://www.cjlogistics.com/ko/tool/parcel/tracking"),
    KOR_CJ_POST_SECOND_URL("https://www.cjlogistics.com/ko/tool/parcel/tracking-detail");

    private String val;

    Url(String val) {
        this.val = val;
    }
    public String getVal() {
        return val;
    }
}
