package com.hansujuan.jcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.hansujuan.jcartadministrationback.constant.ClientExceptionConstant;
import com.hansujuan.jcartadministrationback.dto.in.*;
import com.hansujuan.jcartadministrationback.dto.out.*;
import com.hansujuan.jcartadministrationback.exception.ClientException;
import com.hansujuan.jcartadministrationback.po.Administrator;
import com.hansujuan.jcartadministrationback.service.AdministratorService;
import com.hansujuan.jcartadministrationback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
@CrossOrigin
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/login")
    public AdministratorLoginOutDTO login(AdministratorLoginInDTO administratorLoginInDTO) throws ClientException {

        Administrator administrator = administratorService.selectByUsername(administratorLoginInDTO.getUsername());
        if(administrator==null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE,ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }

        String encryptedPassword = administrator.getEncryptedPassword();
        BCrypt.Result verify = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(), encryptedPassword);
        if(verify.verified){
            AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
            return administratorLoginOutDTO;
        }else{
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE,ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }

    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestParam(required = false) Integer adminstratorId){
        Administrator byId = administratorService.getById(adminstratorId);
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO = new AdministratorGetProfileOutDTO();
        administratorGetProfileOutDTO.setAdministratorId(byId.getAdministratorId());
        administratorGetProfileOutDTO.setUsername(byId.getUsername());
        administratorGetProfileOutDTO.setRealName(byId.getRealName());
        administratorGetProfileOutDTO.setEmail(byId.getEmail());
        administratorGetProfileOutDTO.setAvatarUrl(byId.getAvatarUrl());
        administratorGetProfileOutDTO.setCreateTimestamp(byId.getCreateTime().getTime());

        return administratorGetProfileOutDTO;
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO
    ,@RequestAttribute Integer administratorId){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorId);
        administrator.setRealName(administratorUpdateProfileInDTO.getRealName());
        administrator.setEmail(administratorUpdateProfileInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateProfileInDTO.getAvatarUrl());
        administratorService.update(administrator);
    }

    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
    }

    @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody AdministratorResetPwdInDTO administratorResetPwdInDTO){

    }

    @GetMapping("/getList")
    public PageOutDTO<AdministratorListOutDTO> getList(@RequestParam Integer pageNum){
        return null;
    }

    @GetMapping("/getById")
    public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
        return null;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        return null;
    }

    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){

    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer adminstratorId){

    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> administratorIds){

    }
}
