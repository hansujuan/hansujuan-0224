package com.hansujuan.jcartstoreback.service;

import com.hansujuan.jcartstoreback.dto.in.CustomerRegisterInDTO;
import com.hansujuan.jcartstoreback.po.Customer;

public interface CustomerService {

    Integer register(CustomerRegisterInDTO customerRegisterInDTO);

    Customer getByUsername(String username);

    Customer getById(Integer customerId);

    void update(Customer customer);
}
