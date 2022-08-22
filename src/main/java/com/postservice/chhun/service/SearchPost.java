package com.postservice.chhun.service;

import com.postservice.chhun.dto.DeliveryInfo;
import com.postservice.chhun.dto.ResponseVO;
import org.springframework.stereotype.Service;

@Service
public class SearchPost {

    public ResponseVO searchPost(String postNumber){
        ResponseVO responseVO = new ResponseVO("fail");
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setWho("chhun");
        deliveryInfo.setWhere("화양동");
        responseVO.setStatus("success");
        responseVO.setResult(deliveryInfo);

        return responseVO;
    }
}
