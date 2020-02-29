package com.hansujuan.jcartstoreback.dao;

import com.github.pagehelper.Page;
import com.hansujuan.jcartstoreback.dto.out.ProductListOutDTO;
import com.hansujuan.jcartstoreback.po.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Page<ProductListOutDTO> search();
}