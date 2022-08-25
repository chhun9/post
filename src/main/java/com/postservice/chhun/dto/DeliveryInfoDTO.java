package com.postservice.chhun.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryInfoDTO {
    private String name;
    private List<DeliveryDTO> deliveries;

    public DeliveryInfoDTO(String name, List<DeliveryDTO> deliveries) {
        this.name = name;
        this.deliveries = deliveries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeliveryDTO> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryDTO> deliveries) {
        this.deliveries = deliveries;
    }

}
