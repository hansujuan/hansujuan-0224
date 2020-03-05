package com.hansujuan.jcartstoreback.service;

import com.hansujuan.jcartstoreback.po.OrderHistory;

import java.util.List;

public interface OrderHistoryService {

    List<OrderHistory> getByOrderId(Long orderId);
}
