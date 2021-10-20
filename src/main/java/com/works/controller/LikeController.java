package com.works.controller;


import com.works.entities.Product;
import com.works.repositories.ProductJoinOrder;
import com.works.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/like_mvc")
public class LikeController {

    final ProductRepository pRepo;

    public LikeController(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    @GetMapping("")
    public String like(){
        return "like";
    }



    @ResponseBody
    @GetMapping("/likeResult")
    public List<Product> productLike(){
        List<Product> productLikeResult = pRepo.likeResult();
        return productLikeResult;

    }

    @ResponseBody
    @GetMapping("/dislikeResult")
    public List<Product> productdislike(){
        List<Product> productDislikeResult = pRepo.dislikeResult();
        return productDislikeResult;

    }


}
