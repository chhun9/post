package com.postservice.chhun.dto;

import java.util.regex.Pattern;

public class DeliveryInfo {
    private String who;
    private String where;
    private String whenDate;
    private String whenTime;
    private String status;

    public boolean setInfo(String text) {
        boolean ret = false;
        if (ret = Pattern.matches("^\\d{4}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])$", text)) {
            setWhenDate(text);
        } else if (ret = Pattern.matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", text)) {
            setWhenDate(text);
        } else if (ret = Pattern.matches("^\\d{2}:\\d{2}:\\d{2}$", text)) {
            setWhenTime(text);
        } else if (ret = Pattern.matches("^\\d{2}:\\d{2}$", text)) {
            setWhenTime(text);
        }
        return ret;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    public void setWhenDate(String whenDate) {
        this.whenDate = whenDate;
    }

    public void setWhenTime(String whenTime) {
        this.whenTime = whenTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeliveryInfo{" +
                "where='" + where + '\'' +
                ", whenDate='" + whenDate + '\'' +
                ", whenTime='" + whenTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
