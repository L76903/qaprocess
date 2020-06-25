package com.qaprocess.resultful;

public enum ResultCode {
    SUCCESS(1,"SUCCESS"),
    FAIL(-1,"FAIL"),
    LOGIN_ERROR(1000,"用户名或密码错误"),
    UNKNOWN_ERROR(2000, "未知错误"),
    PARAMETER_ILLEGAL(2001,"参数不合法"),
    TOKEN_INVALID(2002, "无效的Token"),
    TOKEN_SIGNATURE_INVALID(2003, "无效的签名"),
    TOKEN_EXPIRED(2004,"token已过期"),
    TOKEN_MISSION(2005,"token缺失"),
    REFRESH_TOKEN_INVALID(2006,"刷新Token无效"),
    IO_ERROR(5001,"文件处理失败"),
    DATA_MISSION(1032,"DATA_MISSION");

    private Integer status;
    private String message;

    ResultCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
