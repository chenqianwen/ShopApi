package com.shop.api.web.service.authentication;

import com.shop.api.web.model.member.MemberInfo;
import com.shop.api.web.service.member.MemberInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by ygl on 2018/1/26
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private MemberInfoService memberInfoService;


    @Override
    public UserDetails loadUserByUsername(@NotBlank String phone) throws UsernameNotFoundException {
        //TODO
        /**
         * 通过phone去查询会员数据，如果没有查到返回null,无法获得token
         * 如果查到返回User：第一个参数Phone，第二个参数会员ID,第三个参数自定义
         */
        List<MemberInfo> members = memberInfoService.findByPhoneFromMongo(phone);
        if (CollectionUtils.isNotEmpty(members)) {
            String ids = "";
            for (MemberInfo member : members) {
                Long id = member.getId();
                if (StringUtils.isBlank(ids)) {
                    ids = ids+id;
                } else {
                    if (StringUtils.isBlank(ids)) {
                        ids = ids+","+id;
                    }
                }
            }
            User user = new User(phone,ids, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            return user;
        } else {
            return null;
        }
    }
}
