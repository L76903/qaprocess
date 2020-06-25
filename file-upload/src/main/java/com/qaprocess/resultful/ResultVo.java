package com.qaprocess.resultful;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Data
public class ResultVo implements Serializable {
    private Integer status;
    private String message;
    private Object data;


    public ResultVo(){

    }

    public ResultVo(ResultCode resultCode, Object data) {
        this.data = data;
        this.message=resultCode.getMessage();
        this.status = resultCode.getStatus();
    }

    public ResultVo(ResultCode resultCode) {
        this.setStatus(resultCode.getStatus());
        this.setMessage(resultCode.getMessage());
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

    public Object getData() {
        if (data instanceof String){
            return this.data.toString();
        }else if (data instanceof Collection){
            return (Collection)this.data;
        }else if (data instanceof Map){
            return (Map)this.data;
        }else {
            return this.data;
        }
    }

    public void setData(Object data) {
        /*if (data instanceof String){
            this.data=(String)data;
        }else if (data instanceof Collection){
            this.data = (Collection)data;
        }else if (data instanceof Map){
            this.data=(Map)data;

        }else {*/
            this.data=data;
//        }

    }

    //    成功
    public static ResultVo SUCCESS(){
        ResultVo resultVo =new ResultVo(ResultCode.SUCCESS);
        return resultVo;
    }
    public static ResultVo SUCCESS(Object data){
            ResultVo resultVo = SUCCESS();
            resultVo.setData(data);
            return resultVo;
    }
    public static String SUCCESS(String data) throws JsonProcessingException {
        ResultVo resultVo=SUCCESS();
        resultVo.setData(data);
        return new ObjectMapper().writeValueAsString(resultVo);
    }

//    失败
    public static ResultVo FAILURE(ResultCode resultCode){
        ResultVo resultVo =new ResultVo(resultCode);
        return resultVo;
    }
    public static ResultVo FAILURE(Exception e){
        ResultVo resultVo = FAILURE(ResultCode.FAIL);
        resultVo.setMessage(e.getMessage().toString());
//        resultVo.setData(e);
        return resultVo;
    }
    public static ResultVo FAILURE(MyException e){
        ResultVo resultVo =new ResultVo();
        resultVo.setStatus(e.getCode());
        resultVo.setMessage(e.getMessage().toString());
//        resultVo.setData(e);
        return resultVo;
    }



    public static ResultVo FAILURE(ResultCode resultCode, Object data){
        ResultVo resultVo =FAILURE(resultCode);
        resultVo.setData(data);
        return resultVo;
    }


//    转换
    public void setResultCode(ResultCode resultCode) {
        this.setMessage(resultCode);
        this.setStatus(resultCode);
    }

    private void setMessage(ResultCode resultCode) {
        this.message=resultCode.getMessage();
    }

    public void setStatus(ResultCode resultCode) {
        this.status=resultCode.getStatus();
    }




}
