package com.shop.api.web.service.member;

import com.shop.api.web.model.member.MemberInfo;

import java.util.List;

public interface MemberInfoService {

    List<MemberInfo> findByPhoneFromMongo(String phone);

    MemberInfo findByIdFromMongo(Long id);

    MemberInfo save(MemberInfo memberInfo);

    MemberInfo saveToMongo(MemberInfo memberInfo);

}
