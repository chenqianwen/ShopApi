package com.shop.api.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * created by ygl on 2018/1/26
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleErrorException (ErrorException ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("message",ex.getMessage());
        return result;
    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String,Object> unauthorizedException (UnauthorizedException ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("message",ex.getMessage());
        return result;
    }
}
