package com.shop.api.web.service;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * created by ygl on 2018/1/26
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(@NotBlank String phone) throws UsernameNotFoundException {
        //TODO
        /**
         * 通过phone去查询会员数据，如果没有查到返回null,无法获得token
         * 如果查到返回User：第一个参数Phone，第二个参数会员ID,第三个参数自定义
         */
        // 测试数据:
        if (phone.equals("123456")) {
            User user = new User(phone,"1", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            return user;
        }
        return null;
    }
}
