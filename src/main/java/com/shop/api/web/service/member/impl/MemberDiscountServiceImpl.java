package com.shop.api.web.service.member.impl;

import com.shop.api.web.dao.MongoMemberDiscountDao;
import com.shop.api.web.model.member.MemberDiscount;
import com.shop.api.web.service.member.MemberDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberDiscountServiceImpl implements MemberDiscountService{

    @Autowired
    private MongoMemberDiscountDao mongoMemberDiscountDao;

    @Override
    public List<MemberDiscount> findByMemberIdFromMongo(Long memberId) {
        return mongoMemberDiscountDao.findByMemberIdAndValidPeriodFlag(memberId,0);
    }
}
