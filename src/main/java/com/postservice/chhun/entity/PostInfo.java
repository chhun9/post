package com.postservice.chhun.entity;

import com.postservice.chhun.config.KindOfPost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PostInfo {
    @GeneratedValue
    @Id
    private Long id;
    private KindOfPost kindOfPost;
    private String content;
}
