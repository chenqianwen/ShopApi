package com.shop.api.web.service.member;

import com.shop.api.web.model.member.MemberDiscount;

import java.util.List;

public interface MemberDiscountService {
    List<MemberDiscount> findByMemberIdFromMongo(Long memberId);
}
