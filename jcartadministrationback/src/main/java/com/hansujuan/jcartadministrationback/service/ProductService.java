package com.hansujuan.jcartadministrationback.service;

import com.hansujuan.jcartadministrationback.dto.in.ProductCreateInDTO;

public interface ProductService {
    Integer create(ProductCreateInDTO productCreateInDTO);
}
