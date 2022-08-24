package com.postservice.chhun.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PostInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String code;
}
