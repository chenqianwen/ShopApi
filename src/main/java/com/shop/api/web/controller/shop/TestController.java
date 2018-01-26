package com.shop.api.web.controller.shop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by ygl on 2018/1/26
 */
@RestController
public class TestController {

    @GetMapping("/user")
    public String getUser(){
        return "usada";
    }
}
