package com.bao.exception;

import com.bao.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public Response handleBaseException(BaseException e) {
        log.error("BaseException : {},{}", e.getCode(), e.getMessage());
        return Response.builder().code(e.getCode()).msg(e.getMessage()).build();
    }

    @ExceptionHandler(value = Exception.class)
    public Response handleException(Exception e) {
        log.error("Exception : {}", e);
        return Response.builder().code("500").msg(e.getMessage()).build();
    }


}
