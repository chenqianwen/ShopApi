package com.shop.api.web.service.member.impl;

import com.shop.api.web.dao.MemberInfoDao;
import com.shop.api.web.dao.MongoMemberInfoDao;
import com.shop.api.web.model.member.MemberInfo;
import com.shop.api.web.service.member.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoServiceImpl implements MemberInfoService{

    @Autowired
    private MongoMemberInfoDao mongoMemberInfoDao;

    @Autowired
    private MemberInfoDao memberInfoDao;

    @Override
    public List<MemberInfo> findByPhoneFromMongo(String phone) {
        return mongoMemberInfoDao.findByPhoneAndStatus(phone,0);
    }

    @Override
    public MemberInfo findByIdFromMongo(Long id) {
        return mongoMemberInfoDao.findByIdAndStatus(id,0);
    }

    @Override
    public MemberInfo save(MemberInfo memberInfo) {
        return memberInfoDao.save(memberInfo);
    }

    @Override
    public MemberInfo saveToMongo(MemberInfo memberInfo) {
        return mongoMemberInfoDao.save(memberInfo);
    }
}
