package com.qaprocess.resultful;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;


public class MyException extends Exception {


    //错误代码
    Integer code = 500;
    //时间
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    //错误内容
    String error = "error";
    //信息
    String message = "No message available";
    //错误地址
    //String path = "/";


    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public MyException() {
    }

    public MyException(Integer code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public MyException(Integer code, String error, String message, HttpServletResponse httpServletResponse) {
        this.code = code;
        this.error = error;
        this.message = message;
        httpServletResponse.setStatus(this.code);
    }

    public MyException(Integer code) {
        this.code = code;
    }

    public MyException(Integer code, HttpServletResponse httpServletResponse) {
        this.code = code;
        httpServletResponse.setStatus(this.code);
    }
    public MyException(ResultCode resultCode){
        this.code=resultCode.getStatus();
        this.message=resultCode.getMessage();
        this.error=resultCode.getMessage();
    }


    public MyException(String message) {
        this.message = message;
    }
    public MyException(String message, HttpServletResponse httpServletResponse) {
        this.message = message;
        httpServletResponse.setStatus(this.code);
    }

    public MyException(ResultCode resultCode, String message) {

        this.code=resultCode.getStatus();
        this.message=message;
        this.error=resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}