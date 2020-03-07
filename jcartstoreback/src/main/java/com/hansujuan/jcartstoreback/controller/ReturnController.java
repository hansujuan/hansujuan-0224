package com.hansujuan.jcartstoreback.controller;


import com.github.pagehelper.Page;
import com.hansujuan.jcartstoreback.dto.in.ReturnApplyInDTO;
import com.hansujuan.jcartstoreback.dto.out.PageOutDTO;
import com.hansujuan.jcartstoreback.dto.out.ReturnHistoryListOutDTO;
import com.hansujuan.jcartstoreback.dto.out.ReturnListOutDTO;
import com.hansujuan.jcartstoreback.dto.out.ReturnShowOutDTO;
import com.hansujuan.jcartstoreback.enumeration.ReturnStatus;
import com.hansujuan.jcartstoreback.po.Return;
import com.hansujuan.jcartstoreback.po.ReturnHistory;
import com.hansujuan.jcartstoreback.service.ReturnHistoryService;
import com.hansujuan.jcartstoreback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/return")
@CrossOrigin
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @Autowired
    private ReturnHistoryService returnHistoryService;

    @PostMapping("/apply")
    public Integer apply(@RequestBody ReturnApplyInDTO returnApplyInDTO,
                         @RequestAttribute Integer customerId){
        Return aReturn = new Return();
        aReturn.setOrderId(returnApplyInDTO.getOrderId());
        aReturn.setOrderTime(new Date(returnApplyInDTO.getOrderTimestamp()));
        aReturn.setCustomerId(customerId);
        aReturn.setCustomerName(returnApplyInDTO.getCustomerName());
        aReturn.setMobile(returnApplyInDTO.getMobile());
        aReturn.setEmail(returnApplyInDTO.getEmail());
        aReturn.setStatus((byte) ReturnStatus.ToProcess.ordinal());
        aReturn.setProductCode(returnApplyInDTO.getProductCode());
        aReturn.setProductName(returnApplyInDTO.getProductName());
        aReturn.setQuantity(returnApplyInDTO.getQuantity());
        aReturn.setReason(returnApplyInDTO.getReason());
        aReturn.setOpened(returnApplyInDTO.getOpened());
        aReturn.setComment(returnApplyInDTO.getComment());
        Date now = new Date();
        aReturn.setCreateTime(now);
        aReturn.setUpdateTime(now);

        returnService.create(aReturn);
        Integer returnId = aReturn.getReturnId();

        return returnId;
    }

    @GetMapping("/getList")
    public PageOutDTO<ReturnListOutDTO> getList(@RequestAttribute Integer customerId
            , @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        Page<Return> pageByCustomerId = returnService.getPageByCustomerId(customerId, pageNum);
        List<ReturnListOutDTO> collect = pageByCustomerId.stream().map(aReturn -> {
            ReturnListOutDTO returnListOutDTO = new ReturnListOutDTO();
            returnListOutDTO.setReturnId(aReturn.getReturnId());
            returnListOutDTO.setOrderId(aReturn.getOrderId());
            returnListOutDTO.setCustomerId(aReturn.getCustomerId());
            returnListOutDTO.setCustomerName(aReturn.getCustomerName());
            returnListOutDTO.setStatus(aReturn.getStatus());
            returnListOutDTO.setCreateTimestamp(aReturn.getCreateTime().getTime());
            return returnListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<ReturnListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(pageByCustomerId.getTotal());
        pageOutDTO.setPageSize(pageByCustomerId.getPageSize());
        pageOutDTO.setPageNum(pageByCustomerId.getPageNum());
        pageOutDTO.setList(collect);

        return pageOutDTO;
    }

    @GetMapping("/getById")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId){
        Return byId = returnService.getById(returnId);

        ReturnShowOutDTO returnShowOutDTO = new ReturnShowOutDTO();
        returnShowOutDTO.setReturnId(byId.getReturnId());
        returnShowOutDTO.setOrderId(byId.getOrderId());
        returnShowOutDTO.setOrderTimestamp(byId.getOrderTime().getTime());
        returnShowOutDTO.setCustomerName(byId.getCustomerName());
        returnShowOutDTO.setMobile(byId.getMobile());
        returnShowOutDTO.setEmail(byId.getEmail());
        returnShowOutDTO.setStatus(byId.getStatus());
        returnShowOutDTO.setAction(byId.getAction());
        returnShowOutDTO.setProductCode(byId.getProductCode());
        returnShowOutDTO.setProductName(byId.getProductName());
        returnShowOutDTO.setQuantity(byId.getQuantity());
        returnShowOutDTO.setReason(byId.getReason());
        returnShowOutDTO.setComment(byId.getComment());
        returnShowOutDTO.setOpened(byId.getOpened());
        returnShowOutDTO.setCreateTimestamp(byId.getCreateTime().getTime());
        returnShowOutDTO.setUpdateTimestamp(byId.getUpdateTime().getTime());

        List<ReturnHistory> returnHistories = returnHistoryService.getByReturnId(returnId);
        List<ReturnHistoryListOutDTO> returnHistoryListOutDTOS = returnHistories.stream().map(returnHistory -> {
            ReturnHistoryListOutDTO returnHistoryListOutDTO = new ReturnHistoryListOutDTO();
            returnHistoryListOutDTO.setTimestamp(returnHistory.getTime().getTime());
            returnHistoryListOutDTO.setReturnStatus(returnHistory.getReturnStatus());
            returnHistoryListOutDTO.setComment(returnHistory.getComment());
            return returnHistoryListOutDTO;
        }).collect(Collectors.toList());
        returnShowOutDTO.setReturnHistories(returnHistoryListOutDTOS);

        return returnShowOutDTO;
    }

    @PostMapping("/cancel")
    public void cancel(@RequestBody Integer returnId){

    }

}
