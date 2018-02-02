package com.shop.api.web.service.member.impl;

import com.shop.api.web.dao.ShopOrderDao;
import com.shop.api.web.model.member.MemberInfo;
import com.shop.api.web.model.member.ShopOrder;
import com.shop.api.web.service.member.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderServiceImpl implements ShopOrderService{

    @Autowired
    private ShopOrderDao shopOrderDao;

    @Override
    public ShopOrder save(ShopOrder model) {
        model = shopOrderDao.save(model);
        return model;
    }
}
