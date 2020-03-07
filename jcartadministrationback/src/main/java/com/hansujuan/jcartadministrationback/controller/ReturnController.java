package com.hansujuan.jcartadministrationback.controller;

import com.hansujuan.jcartadministrationback.dto.in.ReturnSearchInDTO;
import com.hansujuan.jcartadministrationback.dto.in.ReturnUpdateActionInDTO;
import com.hansujuan.jcartadministrationback.dto.out.PageOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.ReturnListOutDTO;
import com.hansujuan.jcartadministrationback.dto.out.ReturnShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
@CrossOrigin
public class ReturnController {

    @GetMapping("/search")
    public PageOutDTO<ReturnListOutDTO> search(@RequestBody ReturnSearchInDTO returnSearchInDTO,
                                               @RequestParam Integer pageNum){

        return null;
    }

    @GetMapping("/getById")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId){
        return null;
    }

    @PostMapping("/updateAction")
    public void updateAction(@RequestBody ReturnUpdateActionInDTO returnUpdateActionInDTO){

    }

}
