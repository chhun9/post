package com.postservice.chhun.dto;

import java.util.regex.Pattern;

public class DeliveryDTO {
    private String who;
    private String where;
    private String whenDate;
    private String whenTime;
    private String status;

    public DeliveryDTO() {
    }

    public DeliveryDTO(String who, String where, String whenDate, String whenTime, String status) {
        this.who = who;
        this.where = where;
        this.whenDate = whenDate;
        this.whenTime = whenTime;
        this.status = status;
    }

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
        } else if (ret = Pattern.matches("^\\d{2}:\\d{2}:\\d{2}.\\d{2}$", text)) {
            setWhenTime(text);
        } else if (ret = Pattern.matches("^\\d{2}:\\d{2}:\\d{2}.\\d{1}$", text)) {
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

    public String getWho() {
        return who;
    }

    public String getWhere() {
        return where;
    }

    public String getWhenDate() {
        return whenDate;
    }

    public String getWhenTime() {
        return whenTime;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder{
        private String who;
        private String where;
        private String whenDate;
        private String whenTime;
        private String status;

        public Builder setInfo(String text) {
            if (Pattern.matches("^\\d{4}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])$", text)) {
                setWhenDate(text);
            } else if (Pattern.matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", text)) {
                setWhenDate(text);
            } else if (Pattern.matches("^\\d{2}:\\d{2}:\\d{2}$", text)) {
                setWhenTime(text);
            } else if (Pattern.matches("^\\d{2}:\\d{2}$", text)) {
                setWhenTime(text);
            } else if (Pattern.matches("^\\d{2}:\\d{2}:\\d{2}.\\d{2}$", text)) {
                setWhenTime(text);
            } else if (Pattern.matches("^\\d{2}:\\d{2}:\\d{2}.\\d{1}$", text)) {
                setWhenTime(text);
            }
            return this;
        }
        public Builder setWho(String who) {
            this.who = who;
            return this;
        }

        public Builder setWhere(String where) {
            this.where = where;
            return this;
        }

        public Builder setWhenDate(String whenDate) {
            this.whenDate = whenDate;
            return this;
        }

        public Builder setWhenTime(String whenTime) {
            this.whenTime = whenTime;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public DeliveryDTO build() {
            return new DeliveryDTO(who, where, whenDate, whenTime, status);
        }
    }
}
