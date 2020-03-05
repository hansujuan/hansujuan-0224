package com.hansujuan.jcartadministrationback.service.impl;

import com.hansujuan.jcartadministrationback.dao.AddressMapper;
import com.hansujuan.jcartadministrationback.po.Address;
import com.hansujuan.jcartadministrationback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public Address getById(Integer addressId) {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        return address;
    }

    public List<Address> getByCustomerId(Integer customerId) {
        List<Address> addresses = addressMapper.selectByCustomerId(customerId);
        return addresses;
    }
}
