package com.qaprocess.exception;

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
    public MyExceptionResult requestTypeMismatch(Exception e) {
        return new MyException(e.getMessage()).toMyExceptionResult();
    }
    //自定义异常
    @ExceptionHandler(value = {MyException.class})
    public MyExceptionResult requestTypeMismatch(MyException e){
        //httpServletResponse.setStatus(e.code);
        return e.toMyExceptionResult();
    }
    @ExceptionHandler(value = {IOException.class})
    public MyExceptionResult requestTypeMismatch(IOException e){
        return new MyException(MyException.ResponseCodeEnum.IO_ERROR).toMyExceptionResult();
    }

    @ExceptionHandler(MultipartException.class)
    public MyExceptionResult handleError1(MultipartException e) {
        return new MyException(MyException.ResponseCodeEnum.IO_ERROR).toMyExceptionResult();
    }

}
