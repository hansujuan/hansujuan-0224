package com.hansujuan.jcartadministrationback.service.impl;

import com.alibaba.fastjson.JSON;
import com.hansujuan.jcartadministrationback.dao.ProductDetailMapper;
import com.hansujuan.jcartadministrationback.dao.ProductMapper;
import com.hansujuan.jcartadministrationback.dto.in.ProductCreateInDTO;
import com.hansujuan.jcartadministrationback.po.Product;
import com.hansujuan.jcartadministrationback.po.ProductDetail;
import com.hansujuan.jcartadministrationback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailMapper productDetailMapper;


    @Transactional
    public Integer create(ProductCreateInDTO productCreateInDTO) {
        Product product = new Product();
        product.setProductCode(productCreateInDTO.getProductCode());
        product.setProductName(productCreateInDTO.getProductName());
        product.setPrice(productCreateInDTO.getPrice());
        product.setDiscount(productCreateInDTO.getDiscount());
        product.setStockQuantity(productCreateInDTO.getStockQuantity());
        product.setStatus(productCreateInDTO.getStatus());
        product.setMainPicUrl(productCreateInDTO.getMainPicUrl());
        product.setRewordPoints(productCreateInDTO.getRewordPoints());
        product.setSortOrder(productCreateInDTO.getSortOrder());
        String description = productCreateInDTO.getDescription();
        String productAbstract = description.substring(0, Math.min(100, description.length()));
        product.setProductAbstract(productAbstract);
        productMapper.insertSelective(product);

        Integer productId = product.getProductId();
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductId(productId);
        productDetail.setDescription(productCreateInDTO.getDescription());
        List<String> otherPicUrls = productCreateInDTO.getOtherPicUrls();
        productDetail.setOtherPicUrls(JSON.toJSONString(otherPicUrls));
        productDetailMapper.insertSelective(productDetail);

        return productId;
    }
}
