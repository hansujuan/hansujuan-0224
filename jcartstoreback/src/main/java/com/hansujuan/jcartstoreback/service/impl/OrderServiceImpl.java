package com.hansujuan.jcartstoreback.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hansujuan.jcartstoreback.dao.OrderDetailMapper;
import com.hansujuan.jcartstoreback.dao.OrderMapper;
import com.hansujuan.jcartstoreback.dto.in.OrderCheckoutInDTO;
import com.hansujuan.jcartstoreback.dto.in.OrderProductInDTO;
import com.hansujuan.jcartstoreback.dto.out.OrderHistoryListOutDTO;
import com.hansujuan.jcartstoreback.dto.out.OrderShowOutDTO;
import com.hansujuan.jcartstoreback.dto.out.ProductShowOutDTO;
import com.hansujuan.jcartstoreback.enumeration.OrderStatus;
import com.hansujuan.jcartstoreback.po.*;
import com.hansujuan.jcartstoreback.service.AddressService;
import com.hansujuan.jcartstoreback.service.OrderHistoryService;
import com.hansujuan.jcartstoreback.service.OrderService;
import com.hansujuan.jcartstoreback.service.ProductService;
import com.hansujuan.jcartstoreback.vo.OrderProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private AddressService addressService;
    
    @Autowired
    private OrderHistoryService orderHistoryService;

    @Transactional
    public Long checkout(OrderCheckoutInDTO orderCheckoutInDTO, Integer customerId) {

        List<OrderProductInDTO> orderProducts = orderCheckoutInDTO.getOrderProducts();
        List<OrderProductVO> orderProductVOS = orderProducts.stream().map(orderProductInDTO -> {
            Product orderProduct = productService.getById(orderProductInDTO.getProductId());
            OrderProductVO orderProductVO = new OrderProductVO();
            orderProductVO.setProductId(orderProduct.getProductId());
            orderProductVO.setProductCode(orderProduct.getProductCode());
            orderProductVO.setProductName(orderProduct.getProductName());
            orderProductVO.setUnitPrice(orderProduct.getPrice());
            Integer quantity = orderProductInDTO.getQuantity();
            orderProductVO.setQuantity(quantity);

            Double unPrice = orderProduct.getPrice() * orderProduct.getDiscount();
            Double totalPrice = unPrice * quantity;
            orderProductVO.setTotalPrice(totalPrice);
            Integer unitRewordPoints = orderProduct.getRewordPoints();
            orderProductVO.setUnitRewordPoints(unitRewordPoints);

            Integer totalRewordPoints = unitRewordPoints * quantity;
            orderProductVO.setTotalRewordPoints(totalRewordPoints);
            return orderProductVO;

        }).collect(Collectors.toList());

        double allTotalPrice = orderProductVOS.stream().mapToDouble(p -> p.getTotalPrice()).sum();
        int allTotalRewordPoints = orderProductVOS.stream().mapToInt(p -> p.getTotalRewordPoints()).sum();

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setStatus((byte) OrderStatus.ToProcess.ordinal());
        order.setTotalPrice(allTotalPrice);
        order.setRewordPoints(allTotalRewordPoints);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);

        orderMapper.insertSelective(order);
        Long orderId = order.getOrderId();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setShipMethod(orderCheckoutInDTO.getShipMethod());
        orderDetail.setShipPrice(5.0);

        Address shipAddress = addressService.getById(orderCheckoutInDTO.getShipAddressId());
        orderDetail.setShipAddress(shipAddress.getContent());

        orderDetail.setPayMethod(orderCheckoutInDTO.getPayMethod());
        orderDetail.setInvoicePrice(allTotalPrice);
        Address invoiceAddress = addressService.getById(orderCheckoutInDTO.getInvoiceAddressId());
        orderDetail.setInvoiceAddress(invoiceAddress.getContent());

        orderDetail.setComment(orderCheckoutInDTO.getComment());

        orderDetail.setOrderProducts(JSON.toJSONString(orderProductVOS));

        orderDetailMapper.insertSelective(orderDetail);

        return orderId;
    }

    public Page<Order> getByCustomerId(Integer pageNum, Integer customerId) {
        PageHelper.startPage(pageNum,10);
        Page<Order> orders = orderMapper.selectByCustomerId(customerId);

        return orders;
    }

    public OrderShowOutDTO getById(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderId);

        OrderShowOutDTO orderShowOutDTO = new OrderShowOutDTO();
        orderShowOutDTO.setOrderId(orderId);
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

        List<OrderHistory> byOrderId = orderHistoryService.getByOrderId(orderId);
        List<OrderHistoryListOutDTO> orderHistoryListOutDTOS = byOrderId.stream().map(orderHistory -> {
            OrderHistoryListOutDTO orderHistoryListOutDTO = new OrderHistoryListOutDTO();
            orderHistoryListOutDTO.setTimestamp(orderHistory.getTime().getTime());
            orderHistoryListOutDTO.setOrderStatus(orderHistory.getOrderStatus());
            orderHistoryListOutDTO.setComment(orderHistory.getComment());
            return orderHistoryListOutDTO;
        }).collect(Collectors.toList());

        orderShowOutDTO.setOrderHistories(orderHistoryListOutDTOS);

        return orderShowOutDTO;
    }
}
