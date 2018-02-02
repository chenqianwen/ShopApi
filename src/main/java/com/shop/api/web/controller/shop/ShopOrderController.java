package com.shop.api.web.controller.shop;

import com.shop.api.web.dto.ConsumeDto;
import com.shop.api.web.dto.ShopOrderDetailDto;
import com.shop.api.web.dto.ShopOrderDto;
import com.shop.api.web.model.common.Result;
import com.shop.api.web.model.member.ShopOrder;
import com.shop.api.web.model.member.ShopOrderDetail;
import com.shop.api.web.service.member.ShopOrderDetailService;
import com.shop.api.web.service.member.ShopOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class ShopOrderController {

    @Autowired
    private ShopOrderService shopOrderService;

    @Autowired
    private ShopOrderDetailService shopOrderDetailService;


    @PostMapping("/save")
    public Result consume(@RequestBody ShopOrderDto model){

        ShopOrder order = model.toModel(model);
        order= shopOrderService.save(order);

        List<ShopOrderDetailDto> list = model.getList();
        List<ShopOrderDetail> details = new ArrayList<>();
        for (ShopOrderDetailDto dto : list) {
            ShopOrderDetail detail = dto.toModel(dto);
            detail.setOrderId(order.getId());
            details.add(detail);
        }
        shopOrderDetailService.save(details);

        return Result.okResult("保存成功");
    }
}
