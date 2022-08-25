package com.postservice.chhun.service;

import com.postservice.chhun.config.KindOfPost;
import com.postservice.chhun.dto.DeliveryDTO;
import com.postservice.chhun.dto.DeliveryInfoDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchPost {

    public List<DeliveryInfoDTO> searchPost(String postNumber) {
        List<DeliveryInfoDTO> deliveries = new ArrayList<>(new ArrayList<>());

        for (KindOfPost kindOfPost : KindOfPost.values()) {
            SearchInterface searchInterface = null;
            if (KindOfPost.KOR_POST.equals(kindOfPost)) {
                searchInterface = new KPost();
            } else if (KindOfPost.KOR_CJ.equals(kindOfPost)) {
                searchInterface = new CJPost();
            }

            if (null == searchInterface)
                continue;

            List<DeliveryDTO> deliveryList = searchInterface.searchPost(postNumber);

            if (null == deliveryList || deliveryList.isEmpty())
                continue;

            deliveries.add(new DeliveryInfoDTO(kindOfPost.getName(), searchInterface.searchPost(postNumber)));
        }

        return deliveries;
    }


}
