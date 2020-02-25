package com.hansujuan.jcartstoreback.controller;


import com.hansujuan.jcartstoreback.dto.in.ProductSearchInDTO;
import com.hansujuan.jcartstoreback.dto.out.PageOutDTO;
import com.hansujuan.jcartstoreback.dto.out.ProductListOutDTO;
import com.hansujuan.jcartstoreback.dto.out.ProductShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(ProductSearchInDTO productSearchInDTO,
                                                @RequestParam Integer pageNum){
        return null;
    }

    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId){
        return null;
    }

}
