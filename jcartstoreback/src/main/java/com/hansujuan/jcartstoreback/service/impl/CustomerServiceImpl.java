package com.hansujuan.jcartstoreback.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.hansujuan.jcartstoreback.dao.CustomerMapper;
import com.hansujuan.jcartstoreback.dto.in.CustomerRegisterInDTO;
import com.hansujuan.jcartstoreback.enumeration.CustomerStatus;
import com.hansujuan.jcartstoreback.po.Customer;
import com.hansujuan.jcartstoreback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;


    public Integer register(CustomerRegisterInDTO customerRegisterInDTO) {
        Customer customer = new Customer();

        customer.setUsername(customerRegisterInDTO.getUsername());
        customer.setRealName(customerRegisterInDTO.getRealName());
        customer.setEmail(customerRegisterInDTO.getEmail());
        customer.setEmailVerified(false);
        customer.setMobile(customerRegisterInDTO.getMobile());
        customer.setMobileVerified(false);
        customer.setNewsSubscribed(customerRegisterInDTO.getNewsSubscribed());
        customer.setCreateTime(new Date());
        customer.setStatus((byte) CustomerStatus.Enable.ordinal());
        customer.setRewordPoints(0);

        String password = customerRegisterInDTO.getPassword();
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        customer.setEncryptedPassword(bcryptHashString);

        customerMapper.insertSelective(customer);
        Integer customerId = customer.getCustomerId();

        return customerId;
    }

    public Customer getByUsername(String username) {
        Customer customer = customerMapper.selectByUsername(username);
        return customer;
    }

    public Customer getById(Integer customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer;
    }

    public void update(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }
}
