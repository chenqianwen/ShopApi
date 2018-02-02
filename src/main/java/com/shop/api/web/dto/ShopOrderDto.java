package com.shop.api.web.dto;

import com.shop.api.web.model.common.CommonModel;
import com.shop.api.web.model.member.ShopOrder;
import com.shop.api.web.model.member.ShopOrderDetail;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Data
public class ShopOrderDto {

    // 会员id
    private String member_id;

    // 订单号
    private String sn;

    // 支付方式
    private String payment_method_name;

    // 支付方式
    private double total_Price;

    // 支付方式
    private List<ShopOrderDetailDto> list;


    public ShopOrder toModel(ShopOrderDto model) {
        ShopOrder order = new ShopOrder();
        order.setMemberId(model.getMember_id());
        order.setSn(model.getSn());
        order.setPaymentMethodName(model.getPayment_method_name());
        order.setTotalPrice(model.getTotal_Price());
        order.setCreationTime(new Date());
        return order;
    }
}
