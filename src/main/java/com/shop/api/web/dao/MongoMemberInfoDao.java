package com.shop.api.web.dao;

import com.shop.api.web.model.member.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoMemberInfoDao extends MongoRepository<MemberInfo,Long> {

    List<MemberInfo> findByPhoneAndStatus(String phone, int status);

    MemberInfo findByIdAndStatus(Long id, int i);
}

