package com.shop.api.web.service.member.impl;

import com.shop.api.web.dao.ShopOrderDetailDao;
import com.shop.api.web.model.member.ShopOrderDetail;
import com.shop.api.web.service.member.ShopOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderDetailServiceImpl implements ShopOrderDetailService {

    @Autowired
    private ShopOrderDetailDao shopOrderDetailDao;

    @Override
    public List<ShopOrderDetail> save(List<ShopOrderDetail> models) {
        return shopOrderDetailDao.save(models);
    }
}
