package com.shop.api.web.dao;

import com.shop.api.web.model.member.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderDao extends JpaRepository<ShopOrder,Long> {

}

