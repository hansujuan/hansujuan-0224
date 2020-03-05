package com.hansujuan.jcartadministrationback.controller;

import com.github.pagehelper.Page;
import com.hansujuan.jcartadministrationback.dto.in.CustomerSearchInDTO;
import com.hansujuan.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import com.hansujuan.jcartadministrationback.dto.out.CustomerListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.CustomerShowOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.PageOutDTO;
import com.hansujuan.jcartadministrationback.po.Address;
import com.hansujuan.jcartadministrationback.po.Customer;
import com.hansujuan.jcartadministrationback.service.AddressService;
import com.hansujuan.jcartadministrationback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(@RequestBody CustomerSearchInDTO customerSearchInDTO
    , @RequestParam Integer pageNum){

        Page<Customer> search = customerService.search(pageNum);
        List<CustomerListOutDTO> customerListOutDTOS = search.stream().map(customer -> {
            CustomerListOutDTO customerListOutDTO = new CustomerListOutDTO();
            customerListOutDTO.setCustomerId(customer.getCustomerId());
            customerListOutDTO.setUsername(customer.getUsername());
            customerListOutDTO.setRealName(customer.getRealName());
            customerListOutDTO.setMobile(customer.getMobile());
            customerListOutDTO.setEmail(customer.getEmail());
            customerListOutDTO.setStatus(customer.getStatus());
            customerListOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
            return customerListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<CustomerListOutDTO> pageOutDTO = new PageOutDTO<>();

        pageOutDTO.setTotal(search.getTotal());
        pageOutDTO.setPageSize(search.getPageSize());
        pageOutDTO.setPageNum(search.getPageNum());
        pageOutDTO.setList(customerListOutDTOS);

        return pageOutDTO;
    }

    @GetMapping("/getById")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        Customer customer = customerService.getById(customerId);
        CustomerShowOutDTO customerShowOutDTO = new CustomerShowOutDTO();
        customerShowOutDTO.setCustomerId(customerId);
        customerShowOutDTO.setUsername(customer.getUsername());
        customerShowOutDTO.setRealName(customer.getRealName());
        customerShowOutDTO.setMobile(customer.getMobile());
        customerShowOutDTO.setEmail(customer.getEmail());
        customerShowOutDTO.setAvatarUrl(customer.getAvatarUrl());
        customerShowOutDTO.setStatus(customer.getStatus());
        customerShowOutDTO.setRewordPoints(customer.getRewordPoints());
        customerShowOutDTO.setNewsSubscribed(customer.getNewsSubscribed());
        customerShowOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
        customerShowOutDTO.setDefaultAddressId(customer.getDefaultAddressId());

        Address byId = addressService.getById(customer.getDefaultAddressId());
        if(byId!=null){
            customerShowOutDTO.setDefaultAddress(byId.getContent());
        }
        return customerShowOutDTO;
    }

    @PostMapping("/disable")
    public void disable(@RequestParam Integer customerId){

    }

    @PostMapping("/setStatus")
    public void setStatus(@RequestBody CustomerSetStatusInDTO customerSetStatusInDTO){
        customerService.setStatus(customerSetStatusInDTO);
    }

}
