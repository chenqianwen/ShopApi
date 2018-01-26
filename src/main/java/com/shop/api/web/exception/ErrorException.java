package com.shop.api.web.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * created by ygl on 2018/1/26
 */
@Slf4j
public class ErrorException  extends RuntimeException {

    public ErrorException(String msg) {
        super(msg);
        log.info("******************服务器异常***********************");
        log.info(msg);
    }

}
