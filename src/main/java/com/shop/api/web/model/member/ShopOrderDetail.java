package com.shop.api.web.model.member;

import com.shop.api.web.model.common.CommonModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class ShopOrderDetail extends CommonModel    {

    // 订单id
    @Column(name = "order_id")
    private Long orderId;

    // 产品id
    @Column(name = "product")
    private String product;

    // 产品id
    @Column(name = "full_name")
    private String fullName;

    // 数量
    @Column(name = "quantity")
    private  int quantity;

    // 单价
    @Column(name = "price")
    private double price;

}
