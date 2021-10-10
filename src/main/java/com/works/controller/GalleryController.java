package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallery_mvc")
public class GalleryController {
    @GetMapping("")
    public String gallery(){
        return "gallery";
    }
}
