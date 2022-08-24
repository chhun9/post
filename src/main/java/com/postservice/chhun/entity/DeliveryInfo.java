package com.postservice.chhun.entity;

import com.postservice.chhun.config.StringJsonConverter;
import com.postservice.chhun.dto.DeliveryDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class DeliveryInfo {
    @GeneratedValue
    @Id
    private Long id;
    private String postNumber;
    private String postCode;
    private String postStatus;

    @Convert(converter = StringJsonConverter.class)
    @Column(columnDefinition = "json")
    private List<DeliveryDTO> postContent;
}
