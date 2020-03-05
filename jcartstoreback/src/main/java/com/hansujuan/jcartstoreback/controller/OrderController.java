package com.hansujuan.jcartstoreback.controller;


import com.github.pagehelper.Page;
import com.hansujuan.jcartstoreback.dto.in.OrderCheckoutInDTO;
import com.hansujuan.jcartstoreback.dto.out.OrderListOutDTO;
import com.hansujuan.jcartstoreback.dto.out.OrderShowOutDTO;
import com.hansujuan.jcartstoreback.dto.out.PageOutDTO;
import com.hansujuan.jcartstoreback.po.Order;
import com.hansujuan.jcartstoreback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public Long checkout(@RequestBody OrderCheckoutInDTO orderCheckoutInDTO,
                            @RequestAttribute Integer customerId){
        Long checkout = orderService.checkout(orderCheckoutInDTO, customerId);
        return checkout;
    }

    @GetMapping("/getList")
    public PageOutDTO<OrderListOutDTO> getList(@RequestAttribute Integer customerId
    ,@RequestParam(required = false,defaultValue = "1")Integer pageNum){
        Page<Order> byCustomerId = orderService.getByCustomerId(pageNum, customerId);
        List<OrderListOutDTO> collect = byCustomerId.stream().map(order -> {
            OrderListOutDTO orderListOutDTO = new OrderListOutDTO();
            orderListOutDTO.setOrderId(order.getOrderId());
            orderListOutDTO.setStatus(order.getStatus());
            orderListOutDTO.setTotalPrice(order.getTotalPrice());
            orderListOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
            return orderListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<OrderListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(byCustomerId.getTotal());
        pageOutDTO.setPageNum(byCustomerId.getPageNum());
        pageOutDTO.setPageSize(byCustomerId.getPageSize());
        pageOutDTO.setList(collect);

        return pageOutDTO;
    }

    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        OrderShowOutDTO byId = orderService.getById(orderId);
        return byId;
    }
}
