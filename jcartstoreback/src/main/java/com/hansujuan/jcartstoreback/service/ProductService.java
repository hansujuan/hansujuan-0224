package com.hansujuan.jcartstoreback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartstoreback.dto.out.ProductListOutDTO;
import com.hansujuan.jcartstoreback.dto.out.ProductShowOutDTO;

import java.util.List;

public interface ProductService {

    Page<ProductListOutDTO> search(Integer pageNum);

    ProductShowOutDTO getById(Integer productId);
}
