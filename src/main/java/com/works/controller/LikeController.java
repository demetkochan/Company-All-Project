package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/like")
public class LikeController {
    @GetMapping("")
    public String like(){
        return "like";
    }
}
