package com.hansujuan.jcartstoreback.service.impl;

import com.hansujuan.jcartstoreback.dao.AddressMapper;
import com.hansujuan.jcartstoreback.po.Address;
import com.hansujuan.jcartstoreback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public List<Address> getByCustomerId(Integer customerId) {
        List<Address> addresses = addressMapper.selectByCustomerId(customerId);
        return addresses;
    }

    public Integer create(Address address) {
        addressMapper.insertSelective(address);
        Integer addressId = address.getAddressId();
        return addressId;
    }

    public void update(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    public void delete(Integer addressId) {
        addressMapper.deleteByPrimaryKey(addressId);
    }

    public Address getById(Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }
}
