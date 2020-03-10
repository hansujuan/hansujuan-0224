package com.hansujuan.jcartadministrationback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartadministrationback.dto.in.CustomerSearchInDTO;
import com.hansujuan.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import com.hansujuan.jcartadministrationback.po.Customer;

public interface CustomerService {
    Page<Customer> search(CustomerSearchInDTO customerSearchInDTO, Integer pageNum);

    Customer getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);
}
