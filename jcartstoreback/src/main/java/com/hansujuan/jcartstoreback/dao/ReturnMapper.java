package com.hansujuan.jcartstoreback.dao;

import com.github.pagehelper.Page;
import com.hansujuan.jcartstoreback.po.Return;
import org.apache.ibatis.annotations.Param;

public interface ReturnMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> selectPageByCustomerId(@Param("customerId") Integer customerId);
}