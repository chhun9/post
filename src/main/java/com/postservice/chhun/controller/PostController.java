package com.postservice.chhun.controller;

import com.postservice.chhun.config.KindOfPost;
import com.postservice.chhun.dto.DeliveryInfoDTO;
import com.postservice.chhun.service.SearchInterface;
import com.postservice.chhun.service.SearchPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/searchPost/{postId}/{postNumber}")
    public ResponseEntity<DeliveryInfoDTO> searchCode(@PathVariable String postId,
                                                      @PathVariable String postNumber) {
        KindOfPost kindOfPost = Arrays.stream(KindOfPost.values())
                .filter(kop -> kop.toString().equals(postId)).findAny().get();
        if (null == kindOfPost)
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);

        try {
            SearchInterface searchInterface = null;
            DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO(kindOfPost.getName(), searchInterface.searchPost(postNumber));
            return new ResponseEntity<>(deliveryInfoDTO, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
