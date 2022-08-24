package com.postservice.chhun.service;

import com.postservice.chhun.dto.Delivery;
import com.postservice.chhun.dto.DeliveryInfo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchPost {

    public List<DeliveryInfo> searchPost(String postNumber) {
        List<DeliveryInfo> deliveries = new ArrayList<>(new ArrayList<>());

        List<Delivery> deliveryKPost = KPost.searchPost_kpost(postNumber);

        deliveries.add(new DeliveryInfo("kpsot",deliveryKPost));
        return deliveries;
    }


}
