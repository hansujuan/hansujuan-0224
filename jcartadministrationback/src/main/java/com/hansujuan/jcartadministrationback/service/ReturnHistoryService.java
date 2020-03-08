package com.hansujuan.jcartadministrationback.service;

import com.hansujuan.jcartadministrationback.po.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {

    List<ReturnHistory> getListByReturnId(Integer returnId);

    Long create(ReturnHistory returnHistory);
}
