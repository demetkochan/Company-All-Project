package com.works.restcontroller;


import com.works.dto.LikeDto;
import com.works.util.ERest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/like_rest")
public class LikeRestController {

    final LikeDto likeDto;

    public LikeRestController(LikeDto likeDto) {
        this.likeDto = likeDto;
    }

    @GetMapping("/productLikelist")
    public Map<ERest,Object> productLikelist(){
        return likeDto.productLikelist();
    }

    @GetMapping("/productdisLikelist")
    public Map<ERest,Object> productDislikelist(){
        return likeDto.productDisLikelist();
    }

}
