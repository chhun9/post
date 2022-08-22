package com.postservice.chhun.controller;

import com.postservice.chhun.dto.ResponseVO;
import com.postservice.chhun.service.SearchPost;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    SearchPost searchPost;

    public PostController(SearchPost searchPost){
        this.searchPost = searchPost;
    }

    @GetMapping("/searchPost/{postNumber}")
    public ResponseVO searchCode(@PathVariable String postNumber) {
        return searchPost.searchPost(postNumber);
    }
}
