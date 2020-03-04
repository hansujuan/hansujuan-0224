package com.hansujuan.jcartstoreback.controller;


import com.hansujuan.jcartstoreback.dto.in.OrderCheckoutInDTO;
import com.hansujuan.jcartstoreback.dto.out.OrderListOutDTO;
import com.hansujuan.jcartstoreback.dto.out.OrderShowOutDTO;
import com.hansujuan.jcartstoreback.dto.out.PageOutDTO;
import com.hansujuan.jcartstoreback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        return null;
    }

    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        return null;
    }
}
