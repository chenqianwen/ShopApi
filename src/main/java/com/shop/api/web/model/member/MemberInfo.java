package com.shop.api.web.model.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.shop.api.web.model.common.CommonModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
@Data
public class MemberInfo extends CommonModel {

    // 患者编号
    @Column(name = "patient_id")
    private Long patientId;

    // 会员手机号
    @Column(name = "phone")
    private String phone;

    // 会员编号
    @Column(name = "medical_number")
    private String medicalNumber;

    // 会员头像
    @Column(name = "avatar_address")
    private String avatarAddress;

    // 会员姓名
    @Column(name = "member_name")
    private String memberName;

    // 会员等级编号
    @Column(name = "level_id")
    private Long levelId;

    // 会员等级名称
    @Column(name = "level_name")
    private String levelName;

    // 信誉分
    @Column(name = "credit_score")
    private Integer creditScore = 0;

    // 储值账户
    @Column(name = "store_account")
    private BigDecimal storeAccount;

    // 疗程账户
    @Column(name = "treat_account")
    private BigDecimal treatAccount;

    // 赠送账户
    @Column(name = "gift_account")
    private BigDecimal giftAccount;

    // 累计充值金额
    @Column(name = "grand_stored_amount")
    private BigDecimal grandStoredAmount;

    // 累计消费金额
    @Column(name = "grand_consume_amount")
    private BigDecimal grandConsumeAmount;

    // 累计赠送金额
    @Column(name = "grand_gift_amount")
    private BigDecimal grandGiftAmount;

    // 累计会员金额
    @Column(name = "grand_member_amount")
    private BigDecimal grandMemberAmount;

    // 会员状态 0:正常 1:冻结
    @Column(name = "status")
    private Integer status = 0;

    // 转让次数
    @Column(name = "transfer_counts")
    private Integer transferCounts = 0;

    // 备注
    @Column(name = "remark")
    private String remark;

    @JSONField(serialize = false)
    @Column(name = "account_id")
    private Long accountId;

    @Version
    @Column(name = "version")
    private Integer version;
}