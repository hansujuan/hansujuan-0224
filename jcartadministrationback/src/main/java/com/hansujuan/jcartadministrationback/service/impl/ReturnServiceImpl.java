package com.hansujuan.jcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hansujuan.jcartadministrationback.dao.ReturnMapper;
import com.hansujuan.jcartadministrationback.dto.in.ReturnSearchInDTO;
import com.hansujuan.jcartadministrationback.po.Return;
import com.hansujuan.jcartadministrationback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;


    public Page<Return> search(ReturnSearchInDTO returnSearchInDTO,Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Return> search = returnMapper.search(
                returnSearchInDTO.getReturnId(),
                returnSearchInDTO.getOrderId(),
                returnSearchInDTO.getStartTimestamp() == null ? null : new Date(returnSearchInDTO.getStartTimestamp()),
                returnSearchInDTO.getEndTimestamp() == null ? null : new Date(returnSearchInDTO.getEndTimestamp()),
                returnSearchInDTO.getStatus(),
                returnSearchInDTO.getProductCode(),
                returnSearchInDTO.getCustomerName(),
                returnSearchInDTO.getProductName()
        );
        return search;
    }

    public Return getById(Integer returnId) {
        return returnMapper.selectByPrimaryKey(returnId);
    }

    public void update(Return aReturn) {
        returnMapper.updateByPrimaryKeySelective(aReturn);
    }
}
