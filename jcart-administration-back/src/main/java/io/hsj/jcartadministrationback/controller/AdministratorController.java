package io.hsj.jcartadministrationback.controller;

import io.hsj.jcartadministrationback.dao.in.AdministratorLoginInDTO;
import io.hsj.jcartadministrationback.dao.in.AdministratorUpdateProfileInDTO;
import io.hsj.jcartadministrationback.dao.out.AdministratorGetProfileOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @GetMapping("/login")
    public String login(AdministratorLoginInDTO administratorLoginInDTO){
        return null;
    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(Integer adminstratorId){
        return null;
    }

    @PostMapping("/updateProdfile")
    public void updateProdfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){

    }
}
