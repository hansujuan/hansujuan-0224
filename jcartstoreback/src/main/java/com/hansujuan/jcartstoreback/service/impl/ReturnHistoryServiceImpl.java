package com.hansujuan.jcartstoreback.service.impl;

import com.hansujuan.jcartstoreback.dao.ReturnHistoryMapper;
import com.hansujuan.jcartstoreback.po.ReturnHistory;
import com.hansujuan.jcartstoreback.service.ReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnHistoryServiceImpl implements ReturnHistoryService {


    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    public List<ReturnHistory> getByReturnId(Integer returnId) {
        List<ReturnHistory> returnHistories = returnHistoryMapper.selectByReturnId(returnId);
        return returnHistories;
    }
}
