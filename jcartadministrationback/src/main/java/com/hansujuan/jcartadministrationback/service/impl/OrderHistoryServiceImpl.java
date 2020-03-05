package com.hansujuan.jcartadministrationback.service.impl;

import com.hansujuan.jcartadministrationback.dao.OrderHistoryMapper;
import com.hansujuan.jcartadministrationback.po.OrderHistory;
import com.hansujuan.jcartadministrationback.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    public List<OrderHistory> getByOrderId(Long orderId) {
        return orderHistoryMapper.selectByOrderId(orderId);
    }

    @Transactional
    public Long create(OrderHistory orderHistory) {
        orderHistoryMapper.insertSelective(orderHistory);
        return orderHistory.getOrderHistoryId();
    }
}
