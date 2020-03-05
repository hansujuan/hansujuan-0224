package com.hansujuan.jcartstoreback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartstoreback.dto.in.OrderCheckoutInDTO;
import com.hansujuan.jcartstoreback.dto.out.OrderShowOutDTO;
import com.hansujuan.jcartstoreback.po.Order;

public interface OrderService {
    Long checkout(OrderCheckoutInDTO orderCheckoutInDTO,
                  Integer customerId);

    Page<Order> getByCustomerId(Integer pageNum, Integer customerId);

    OrderShowOutDTO getById(Long orderId);
}
