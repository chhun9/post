package com.postservice.chhun.repository;

import com.postservice.chhun.entity.PostInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostInfoRepository extends JpaRepository<PostInfo, String> {
}
