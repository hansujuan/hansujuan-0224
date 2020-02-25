package com.hansujuan.jcartadministrationback.controller;

import com.hansujuan.jcartadministrationback.dto.in.CustomerSearchInDTO;
import com.hansujuan.jcartadministrationback.dto.out.CustomerListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.CustomerShowOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(@RequestBody CustomerSearchInDTO customerSearchInDTO
    , @RequestParam Integer pageNum){
        return null;
    }

    @GetMapping("/getById")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        return null;
    }

    @PostMapping("/disable")
    public void disable(@RequestParam Integer customerId){

    }

}
