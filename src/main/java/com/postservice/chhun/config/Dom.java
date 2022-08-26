package com.postservice.chhun.config;

public enum Dom {
    TABLE("table"),
    TABLE_BODY("tbody"),
    TABLE_ROW("tr"),
    TABLE_COLUMN("td"),
    SPAN("span"),
    ANCHOR("a"),
    CSRF("csrf"),
    BODY("body"),
    PROCESS_TABLE_ID("#processTable"),
    EVENT_NAME_CLASS(".evtnm"),
    KOR_POST_POSTNUMBER("sid1"),
    KOR_CJ_POST_SET_COOKIE("set-cookie"),
    KOR_CJ_POST_CSRF("input[name='_csrf']"),
    KOR_CJ_POST_POSTNUMBER("paramInvcNo"),
    KOR_CJ_POST_D_RESULT_MAP("parcelDetailResultMap"),
    KOR_CJ_POST_D_RESULT_LIST("resultList"),
    KOR_CJ_POST_D_TIME("dTime"),
    KOR_CJ_POST_D_REG_NAME("regBranNm"),
    KOR_CJ_POST_D_SCAN_NAME("scanNm"),
    KOR_CJ_POST_COOKIE("Cookie");


    private String val;

    Dom(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public String preGet(String str) {
        return str + val;
    }

    public static String get(Dom... doms) {
        String ret = "";
        for (Dom dom : doms) {
            ret += dom.getVal();
        }
        return ret;
    }

    public static String get(String diameter, Dom... doms) {
        String ret = "";
        for (Dom dom : doms) {
            ret += dom.getVal();
            ret += diameter;
        }
        return ret;
    }
}
