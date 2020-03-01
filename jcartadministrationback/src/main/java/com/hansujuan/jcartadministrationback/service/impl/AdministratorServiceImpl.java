package com.hansujuan.jcartadministrationback.service.impl;

import com.hansujuan.jcartadministrationback.dao.AdministratorMapper;
import com.hansujuan.jcartadministrationback.po.Administrator;
import com.hansujuan.jcartadministrationback.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;


    public Administrator selectByUsername(String username) {
        return administratorMapper.selectByUsername(username);
    }

    public Administrator getById(Integer administratorId) {
        return administratorMapper.selectByPrimaryKey(administratorId);
    }

    public void update(Administrator administrator) {
        administratorMapper.updateByPrimaryKeySelective(administrator);
    }
}
