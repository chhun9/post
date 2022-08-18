package com.postservice.chhun.controller;

import com.postservice.chhun.dto.ResponseVO;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @GetMapping("/{searchCode}")
    public ResponseVO searchCode(@PathVariable String searchCode) {
        ResponseVO responseVO = new ResponseVO("fail");
        return responseVO;
    }
}
