package com.hansujuan.jcartstoreback.service;

import com.hansujuan.jcartstoreback.dto.in.OrderCheckoutInDTO;

public interface OrderService {
    Long checkout(OrderCheckoutInDTO orderCheckoutInDTO,
                  Integer customerId);
}
