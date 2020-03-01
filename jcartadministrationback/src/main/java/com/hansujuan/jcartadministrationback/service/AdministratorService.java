package com.hansujuan.jcartadministrationback.service;

import com.hansujuan.jcartadministrationback.po.Administrator;

public interface AdministratorService {

    Administrator selectByUsername(String username);

    Administrator getById(Integer administratorId);

    void update(Administrator administrator);
}
