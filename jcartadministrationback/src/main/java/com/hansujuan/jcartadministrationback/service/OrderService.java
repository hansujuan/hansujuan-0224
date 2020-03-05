package com.hansujuan.jcartadministrationback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartadministrationback.dto.out.OrderListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.OrderShowOutDTO;

public interface OrderService {
    Page<OrderListOutDTO> search(Integer pageNum);

    OrderShowOutDTO getById(Long orderId);
}
