package com.qaprocess.vo;

import lombok.Data;

/**
 * 可以使用ResponseCodeEnum作为模板
 * @param <T>
 */
@Data
public class ResponseResult<T>{
    private int code = 200;
    private T data;

    public ResponseResult(){

    }
    public ResponseResult(T data){
        this.code=200;
        this.data=data;
    }
    public ResponseResult(int code){
        this.code=code;
    }
    public ResponseResult(int code, T data){
        this.code=code;
        this.data=data;
    }

}
