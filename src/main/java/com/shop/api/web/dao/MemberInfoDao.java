package com.shop.api.web.dao;

import com.shop.api.web.model.member.MemberDiscount;
import com.shop.api.web.model.member.MemberInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInfoDao extends MongoRepository<MemberInfo,Long> {

}

