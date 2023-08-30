package com.mjl.blog.framework.MVC.handler;

import com.mjl.blog.common.exception.ServiceException;
import com.mjl.blog.common.pojo.CommonResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public CommonResult<?> ServiceExceptionHandler(ServiceException ex){
        log.warn("[ServiceException]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }
}
