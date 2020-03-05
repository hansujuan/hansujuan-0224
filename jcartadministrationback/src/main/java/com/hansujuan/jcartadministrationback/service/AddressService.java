package com.hansujuan.jcartadministrationback.service;

import com.hansujuan.jcartadministrationback.po.Address;

import java.util.List;

public interface AddressService {
    Address getById(Integer addressId);

    List<Address> getByCustomerId(Integer customerId);
}
