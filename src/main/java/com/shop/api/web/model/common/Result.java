package com.shop.api.web.model.common;

import lombok.Data;

@Data
public class Result {

    // -1 错误 1 正确
    private int code;

    private Object response;

    public Result(int code, Object response) {
        this.code = code;
        this.response = response;
    }

    public static Result errorResult(Object object) {
        return new Result(-1,object);
    }

    public static Result okResult(Object object) {
        return new Result(0,object);
    }
}
