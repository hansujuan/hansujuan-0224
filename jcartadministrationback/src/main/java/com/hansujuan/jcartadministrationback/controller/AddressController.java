package com.hansujuan.jcartadministrationback.controller;


import com.hansujuan.jcartadministrationback.dto.out.AddressListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.AddressShowOutDTO;
import com.hansujuan.jcartadministrationback.po.Address;
import com.hansujuan.jcartadministrationback.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getListByCustomerId")
    public List<AddressListOutDTO> getListByCustomerId(@RequestParam Integer customerId){
        List<Address> byCustomerId = addressService.getByCustomerId(customerId);
        List<AddressListOutDTO> collect = byCustomerId.stream().map(address -> {
            AddressListOutDTO addressListOutDTO = new AddressListOutDTO();
            addressListOutDTO.setAddressId(address.getAddressId());
            addressListOutDTO.setReceiverName(address.getReceiverName());
            addressListOutDTO.setReceiverMobile(address.getReceiverMobile());
            addressListOutDTO.setContent(address.getContent());
            addressListOutDTO.setTag(address.getTag());
            return addressListOutDTO;
        }).collect(Collectors.toList());

        return collect;
    }

    @GetMapping("/getById")
    public AddressShowOutDTO getById(@RequestParam Integer addressId){
        Address byId = addressService.getById(addressId);
        AddressShowOutDTO addressShowOutDTO = new AddressShowOutDTO();
        addressShowOutDTO.setAddressId(byId.getAddressId());
        addressShowOutDTO.setReceiverName(byId.getReceiverName());
        addressShowOutDTO.setReceiverMobile(byId.getReceiverMobile());
        addressShowOutDTO.setContent(byId.getContent());
        addressShowOutDTO.setTag(byId.getTag());
        return addressShowOutDTO;
    }
}
