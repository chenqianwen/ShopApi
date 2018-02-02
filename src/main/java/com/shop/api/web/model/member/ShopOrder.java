package com.shop.api.web.model.member;

import com.shop.api.web.model.common.CommonModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class ShopOrder extends CommonModel {

    // 会员id
    @Column(name = "member_id")
    private String memberId;

    // 订单号
    @Column(name = "sn")
    private String sn;

    // 支付方式
    @Column(name = "payment_method_name")
    private String paymentMethodName;

    // 支付方式
    @Column(name = "total_Price")
    private double totalPrice;

}
