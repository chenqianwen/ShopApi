package com.shop.api.web.controller.shop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by ygl on 2018/1/26
 */
@RestController
@RequestMapping("/member")
public class MemberUserController {

    @GetMapping("/{id}")
    public String findOne(@PathVariable String id){
        return "测试会员用户ID:"+id;
    }
}
