package com.hansujuan.jcartadministrationback.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hansujuan.jcartadministrationback.dao.OrderDetailMapper;
import com.hansujuan.jcartadministrationback.dao.OrderMapper;
import com.hansujuan.jcartadministrationback.dto.out.OrderListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.OrderShowOutDTO;
import com.hansujuan.jcartadministrationback.po.Customer;
import com.hansujuan.jcartadministrationback.po.Order;
import com.hansujuan.jcartadministrationback.po.OrderDetail;
import com.hansujuan.jcartadministrationback.service.CustomerService;
import com.hansujuan.jcartadministrationback.service.OrderService;
import com.hansujuan.jcartadministrationback.vo.OrderProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private CustomerService customerService;

    public Page<OrderListOutDTO> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<OrderListOutDTO> search = orderMapper.search();
        return search;
    }

    public OrderShowOutDTO getById(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderId);

        OrderShowOutDTO orderShowOutDTO = new OrderShowOutDTO();
        orderShowOutDTO.setOrderId(orderId);
        orderShowOutDTO.setCustomerId(order.getCustomerId());

        Customer customer = customerService.getById(order.getCustomerId());

        orderShowOutDTO.setCustomerName(customer.getRealName());
        orderShowOutDTO.setStatus(order.getStatus());
        orderShowOutDTO.setTotalPrice(order.getTotalPrice());
        orderShowOutDTO.setRewordPoints(order.getRewordPoints());
        orderShowOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
        orderShowOutDTO.setUpdateTimestamp(order.getUpdateTime().getTime());

        orderShowOutDTO.setShipMethod(orderDetail.getShipMethod());
        orderShowOutDTO.setShipAddress(orderDetail.getShipAddress());
        orderShowOutDTO.setShipPrice(orderDetail.getShipPrice());
        orderShowOutDTO.setPayMethod(orderDetail.getPayMethod());
        orderShowOutDTO.setInvoiceAddress(orderDetail.getInvoiceAddress());
        orderShowOutDTO.setInvoicePrice(orderDetail.getInvoicePrice());
        orderShowOutDTO.setComment(orderDetail.getComment());

        List<OrderProductVO> orderProductVOS = JSON.parseArray(orderDetail.getOrderProducts(), OrderProductVO.class);
        orderShowOutDTO.setOrderProducts(orderProductVOS);

        return orderShowOutDTO;
    }
}
