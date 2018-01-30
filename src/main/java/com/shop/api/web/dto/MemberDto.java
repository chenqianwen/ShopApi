package com.shop.api.web.dto;

import com.shop.api.web.model.member.MemberInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberDto {
    /**
     * 会员ID
     */
    private Long id;
    /**
     * 会员账户
     */
    private String medicalNumber;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 储值账户金额
     */
    private BigDecimal storeAccount;
    /**
     * 会员的来源:南京，广州
     */
    private String source;
    /**
     * 折扣
     */
    private Double discount;

    public MemberDto(MemberInfo memberInfo, double discount) {
        if (memberInfo == null) {
            return;
        }
        this.id = memberInfo.getId();
        this.medicalNumber = memberInfo.getMedicalNumber();
        this.memberName = memberInfo.getMemberName();
        this.storeAccount = memberInfo.getStoreAccount();
        this.discount = discount;
        this.source = memberInfo.getAccountId() == 1?"南京":"广州";
    }
}
