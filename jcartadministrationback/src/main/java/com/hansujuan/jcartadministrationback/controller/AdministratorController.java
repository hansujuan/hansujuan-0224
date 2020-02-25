package com.hansujuan.jcartadministrationback.controller;

import com.hansujuan.jcartadministrationback.dto.in.AdministratorLoginInDTO;
import com.hansujuan.jcartadministrationback.dto.in.AdministratorUpdateProfileInDTO;
import com.hansujuan.jcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @GetMapping("/login")
    public String login(AdministratorLoginInDTO administratorLoginInDTO){
        return null;
    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestParam(required = false) Integer adminstratorId){
        return null;
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){

    }

    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
    }



}
