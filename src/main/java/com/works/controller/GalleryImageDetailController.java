package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/galleryimage_mvc")
public class GalleryImageDetailController {
    @GetMapping("")
    public String galleryimage(){
        return "galleryImageDetail";
    }
}
