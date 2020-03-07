package com.hansujuan.jcartstoreback.service;

import com.hansujuan.jcartstoreback.po.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {
    List<ReturnHistory> getByReturnId(Integer returnId);
}
