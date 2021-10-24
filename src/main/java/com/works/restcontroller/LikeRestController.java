package com.works.restcontroller;


import com.works.dto.LikeDto;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/like_rest")
@Api(value ="LikeRestController",authorizations ={@Authorization(value = "basicAuth")})

public class LikeRestController {

    final LikeDto likeDto;

    public LikeRestController(LikeDto likeDto) {
        this.likeDto = likeDto;
    }

    @ApiOperation("Beğenilen ürünlerin listelenmesi")
    @GetMapping("/productLikelist")
    public Map<ERest,Object> productLikelist(){
        return likeDto.productLikelist();
    }

    @ApiOperation("Beğenilmeyen ürünlerin listelenmesi")
    @GetMapping("/productdisLikelist")
    public Map<ERest,Object> productDislikelist(){
        return likeDto.productDisLikelist();
    }

}
