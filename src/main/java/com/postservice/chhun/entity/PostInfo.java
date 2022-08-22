package com.postservice.chhun.entity;

import com.postservice.chhun.config.StringJsonConverter;
import com.postservice.chhun.dto.DeliveryInfo;

import javax.persistence.*;
import java.util.List;

@Entity
public class PostInfo {
    @GeneratedValue
    @Id
    private Long id;
    private String postNumber;
    private String postCode;
    private String postStatus;

    @Convert(converter = StringJsonConverter.class)
    @Column(columnDefinition = "json")
    private List<DeliveryInfo> postContent;
}
