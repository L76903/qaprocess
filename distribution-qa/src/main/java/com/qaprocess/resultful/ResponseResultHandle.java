package com.qaprocess.resultful;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ResponseResultHandle implements ResponseBodyAdvice<Object> {


    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = sra.getRequest();
//        判断请求，是否有包装标记
        ResponseResult responseResultAnn = (ResponseResult)request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResultAnn==null?false:true;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("进入返回体重写格式处理中。。。");
        if (body instanceof MyException){
            log.info("返回值 异常 作包装 处理。。。");
            MyException errorResult=(MyException)body;
            return  ResultVo.FAILURE(errorResult);
        }else if (body instanceof String){
            try {
               body = ResultVo.SUCCESS((String)body);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            response.getHeaders().set("Content-Type","application/json");
            return body;
        }else {
            return ResultVo.SUCCESS(body);
        }

    }
}
