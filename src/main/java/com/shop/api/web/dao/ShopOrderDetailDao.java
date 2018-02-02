package com.shop.api.web.dao;

import com.shop.api.web.model.member.ShopOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderDetailDao extends JpaRepository<ShopOrderDetail,Long> {

}

