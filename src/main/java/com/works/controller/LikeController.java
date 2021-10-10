package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/like_mvc")
public class LikeController {
    @GetMapping("")
    public String like(){
        return "like";
    }
}
