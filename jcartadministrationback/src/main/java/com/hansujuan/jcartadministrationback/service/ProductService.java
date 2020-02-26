package com.hansujuan.jcartadministrationback.service;

import com.github.pagehelper.Page;
import com.hansujuan.jcartadministrationback.dto.in.ProductCreateInDTO;
import com.hansujuan.jcartadministrationback.dto.in.ProductUpdateInDTO;
import com.hansujuan.jcartadministrationback.dto.out.ProductListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.ProductShowOutDTO;

import java.util.List;

public interface ProductService {

    Integer create(ProductCreateInDTO productCreateInDTO);

    void update(ProductUpdateInDTO productUpdateInDTO);

    void delete(Integer productId);

    void batchDelete(List<Integer> productIds);

    Page<ProductListOutDTO> search(Integer pageNum);

    ProductShowOutDTO getById(Integer productId);
}
