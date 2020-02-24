package io.hsj.jcartstoreback.controller;


import io.hsj.jcartstoreback.dao.Consumer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @PostMapping("/register")
    public Integer consumerRegister(@RequestBody Consumer consumer){
        return null;
    }

    @GetMapping("/login")
    public String consumerLogin(String username,String password){
        return null;
    }

    @PostMapping("/changepassword")
    public String consumerChangepassword(String originPwd,String newPwd){
        return null;
    }
}
