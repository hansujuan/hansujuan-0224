package com.hansujuan.jcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hansujuan.jcartadministrationback.dao.CustomerMapper;
import com.hansujuan.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import com.hansujuan.jcartadministrationback.po.Customer;
import com.hansujuan.jcartadministrationback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;


    public Page<Customer> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Customer> search = customerMapper.search();
        return search;
    }

    public Customer getById(Integer customerId) {

        return customerMapper.selectByPrimaryKey(customerId);
    }

    public void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerSetStatusInDTO.getCustomerId());
        customer.setStatus(customerSetStatusInDTO.getStatus());
        customerMapper.updateByPrimaryKeySelective(customer);
    }
}
