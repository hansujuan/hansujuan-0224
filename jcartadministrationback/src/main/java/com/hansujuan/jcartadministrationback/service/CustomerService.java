package com.hansujuan.jcartadministrationback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import com.hansujuan.jcartadministrationback.po.Customer;

public interface CustomerService {
    Page<Customer> search(Integer pageNum);

    Customer getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);
}
