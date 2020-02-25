package com.hansujuan.jcartadministrationback.controller;


import com.hansujuan.jcartadministrationback.dto.in.ProductCreateInDTO;
import com.hansujuan.jcartadministrationback.dto.in.ProductSearchInDTO;
import com.hansujuan.jcartadministrationback.dto.in.ProductUpdateInDTO;
import com.hansujuan.jcartadministrationback.dto.out.PageOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.ProductListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.ProductShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(@RequestBody ProductSearchInDTO productSearchInDTO
    , @RequestParam Integer pageNum){
        return null;
    }

    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId){
        return null;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody ProductCreateInDTO p ){
        return null;
    }


    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateInDTO productUpdateInDTO){

    }
}
