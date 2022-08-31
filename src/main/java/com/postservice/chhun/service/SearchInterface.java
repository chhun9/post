package com.postservice.chhun.service;

import com.postservice.chhun.dto.DeliveryDTO;

import java.util.List;

public interface SearchInterface {
    List<DeliveryDTO> searchPost(String postNumber);
}
