package com.hansujuan.jcartstoreback.service;

import com.hansujuan.jcartstoreback.po.Address;

import java.util.List;

public interface AddressService {

    List<Address> getByCustomerId(Integer customerId);

    Integer create(Address address);

    void update(Address address);

    void delete(Integer addressId);
}
