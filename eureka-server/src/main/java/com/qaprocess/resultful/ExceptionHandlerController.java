package com.qaprocess.resultful;


import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {
    @Nullable
    public MyException resolveException(Exception e){
        return new MyException(404,"null",e.getMessage().toString());
    }
    //通用异常
    @ExceptionHandler(value = {Exception.class})
    public MyException requestTypeMismatch(Exception e) {
        return new MyException(e.getMessage());
    }
    //自定义异常
    @ExceptionHandler(value = {MyException.class})
    public MyException requestTypeMismatch(MyException e){
        //httpServletResponse.setStatus(e.code);
        return e;
    }
    @ExceptionHandler(value = {IOException.class})
    public MyException requestTypeMismatch(IOException e){
        return new MyException(ResultCode.IO_ERROR);
    }

    @ExceptionHandler(MultipartException.class)
    public MyException handleError1(MultipartException e) {
        return new MyException(ResultCode.IO_ERROR);
    }

}
