package com.shop.api.web.model.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.shop.api.web.model.common.CommonModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

//  会员折扣
@Entity
@Data
public class MemberDiscount extends CommonModel {

    // 会员Id
    @Column(name = "member_id")
    private Long memberId;

    // 治疗方案中家庭病房ID
    @Column(name = "treat_plan_home_id")
    private Long treatPlanHomeId;

    // 治疗方案中家庭病房明细ID
    @Column(name = "treat_plan_home_detail_id")
    private Long treatPlanHomeDetailId;

    // 折扣名称
    @Column(name = "discount_name")
    private String discountName;

    // 折扣种类 1: 全品类  2：非全品类
    @Column(name = "discount_specific")
    private Integer discountSpecific;

    // 项目：1，产品：2，手术：3
    @Column(name = "item_type")
    private int itemType;

    // 具体：1，大类：2，小类：3
    @Column(name = "class_type")
    private int classType;

    // 大小类分类或者具体itemId
    @Column(name = "item_specific")
    private String itemSpecific;


    // 折扣类型，1：折扣，2：次数
    @Column(name = "discount_type")
    private int discountType;

    // 折扣率
    @Column(name = "discount_rate")
    private double discountRate;

    // 折扣次数
    @Column(name = "discount_counts")
    private int discountCounts;

    // 使用次数
    @Column(name = "use_counts")
    private int useCounts = 0;

    // 有效期开始时间
    @Column(name = "start_time")
    private Date startTime;

    // 有效期结束时间
    @Column(name = "end_time")
    private Date endTime;

    // 有效期标识 0:有效 1：失效
    @Column(name = "valid_period_flag")
    private int validPeriodFlag = 0;

    // 折扣说明
    @Column(name = "description")
    private String description;

    @JSONField(serialize = false)
    @Column(name = "account_id")
    private Long accountId;
}
