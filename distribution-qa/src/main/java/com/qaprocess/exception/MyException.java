package com.qaprocess.exception;

import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;


public class MyException extends Exception {
    public enum ResponseCodeEnum {
        FAIL(-1, "error","失败"),
        LOGIN_ERROR(1000,"error", "用户名或密码错误"),
        UNKNOWN_ERROR(2000, "error","未知错误"),
        PARAMETER_ILLEGAL(2001,"error", "参数不合法"),
        TOKEN_INVALID(2002, "error","无效的Token"),
        TOKEN_SIGNATURE_INVALID(2003,"error", "无效的签名"),
        TOKEN_EXPIRED(2004, "error","token已过期"),
        TOKEN_MISSION(2005, "error","token缺失"),
        REFRESH_TOKEN_INVALID(2006, "error","刷新Token无效"),
        IO_ERROR(5001,"error","文件处理失败"),
        DATA_MISSION(1032,"error","DATA_MISSION");


        private int code;
        //错误内容
        String error = "Not Found";
        //信息
        String message = "No message available";

        ResponseCodeEnum(int code,String message,String error) {
            this.code = code;
            this.message = message;
            this.error=error;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

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


    public MyException(String message) {
        this.message = message;
    }
    public MyException(String message, HttpServletResponse httpServletResponse) {
        this.message = message;
        httpServletResponse.setStatus(this.code);
    }

    public MyException(ResponseCodeEnum responseCodeEnum){
        this.code=responseCodeEnum.getCode();
        this.message=responseCodeEnum.getMessage();
        this.error=responseCodeEnum.getError();

    }
    public MyException(ResponseCodeEnum responseCodeEnum, HttpServletResponse httpServletResponse){
        this.code=responseCodeEnum.getCode();
        this.message=responseCodeEnum.getMessage();
        this.error=responseCodeEnum.getError();
        httpServletResponse.setStatus(this.code);

    }
    public MyException(ResponseCodeEnum responseCodeEnum, String message){
        this.code=responseCodeEnum.getCode();
        this.message=message;
        this.error=responseCodeEnum.getError();

    }
    public MyException(ResponseCodeEnum responseCodeEnum, String message, HttpServletResponse httpServletResponse){
        this.code=responseCodeEnum.getCode();
        this.message=message;
        this.error=responseCodeEnum.getError();
        httpServletResponse.setStatus(this.code);

    }
    public MyExceptionResult toMyExceptionResult(){
        return MyExceptionResult.valueOf(this);
    }

}

@Data
class MyExceptionResult {
    //错误代码
    Integer code = 500;
    //时间
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    //错误内容
    String error = "Not Found";
    //信息
    String message = "No message available";
    //错误地址
    //String path = "/";

    /*    public MyExceptionResult valueOf(MyException e){
            this.code=e.code;
            this.timestamp=e.timestamp;
            this.error=e.error;
            this.message=e.message;
            return this;
        }*/
    public static MyExceptionResult valueOf(MyException e){
        MyExceptionResult myExceptionResult=new MyExceptionResult();
        myExceptionResult.code=e.code;
        myExceptionResult.timestamp=e.timestamp;
        myExceptionResult.error=e.error;
        myExceptionResult.message=e.message;
        return myExceptionResult;
    }


}
