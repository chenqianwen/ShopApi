package com.shop.api.web.dto;

import com.shop.api.web.model.common.CommonModel;
import com.shop.api.web.model.member.ShopOrder;
import com.shop.api.web.model.member.ShopOrderDetail;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
public class ShopOrderDetailDto {

    // 产品id
    private String product;

    // 产品名称
    private String full_name;

    // 数量
    private  int quantity;

    // 单价
    private double price;

    public ShopOrderDetail toModel(ShopOrderDetailDto dto) {
        ShopOrderDetail detail = new ShopOrderDetail();
        detail.setProduct(dto.getProduct());
        detail.setFullName(dto.getFull_name());
        detail.setQuantity(dto.getQuantity());
        detail.setPrice(dto.getPrice());
        detail.setCreationTime(new Date());
        return detail;
    }
}
