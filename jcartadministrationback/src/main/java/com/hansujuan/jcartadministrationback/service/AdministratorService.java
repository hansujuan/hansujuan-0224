package com.hansujuan.jcartadministrationback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartadministrationback.po.Administrator;

import java.util.List;

public interface AdministratorService {

    Administrator selectByUsername(String username);

    Administrator getById(Integer administratorId);

    void update(Administrator administrator);

    Integer create(Administrator administrator);

    void delete(Integer administratorId);

    void batchDelete(List<Integer> administratorIds);

    Page<Administrator> getList(Integer pageNum);

    Administrator getByEmail(String email);
}
