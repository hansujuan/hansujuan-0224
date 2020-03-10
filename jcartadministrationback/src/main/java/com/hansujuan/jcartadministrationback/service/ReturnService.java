package com.hansujuan.jcartadministrationback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartadministrationback.dto.in.ReturnSearchInDTO;
import com.hansujuan.jcartadministrationback.po.Return;

public interface ReturnService {

    Page<Return> search(ReturnSearchInDTO returnSearchInDTO,Integer pageNum);

    Return getById(Integer returnId);

    void update(Return aReturn);
}
