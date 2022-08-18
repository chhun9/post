package com.postservice.chhun.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVO {
    private String status;
    private String reason;
    private String errorCode;
    private Object result;

    public static final String SUCCEES = "success";
    public static final String FAIL = "fail";

    public ResponseVO(String status) {
        this.status = status;
    }

    public ResponseVO(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", result=" + result +
                '}';
    }
}
