package com.postservice.chhun.controller;

import com.postservice.chhun.dto.DeliveryInfoDTO;
import com.postservice.chhun.service.SearchPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    SearchPost searchPost;

    public PostController(SearchPost searchPost) {
        this.searchPost = searchPost;
    }

    @GetMapping("/searchPost/{postNumber}")
    public ResponseEntity<List<DeliveryInfoDTO>> searchCode(@PathVariable String postNumber) {
        return new ResponseEntity<>(searchPost.searchPost(postNumber), HttpStatus.OK);
    }
}
