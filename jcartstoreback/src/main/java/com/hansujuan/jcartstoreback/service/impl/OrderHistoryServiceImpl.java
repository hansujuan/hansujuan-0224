package com.hansujuan.jcartstoreback.service.impl;

import com.hansujuan.jcartstoreback.dao.OrderHistoryMapper;
import com.hansujuan.jcartstoreback.po.OrderHistory;
import com.hansujuan.jcartstoreback.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    public List<OrderHistory> getByOrderId(Long orderId) {
        List<OrderHistory> orderHistories = orderHistoryMapper.selectByOrderId(orderId);
        return orderHistories;
    }
}
