package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/galleryDetail")
public class GalleryDetailController {
    @GetMapping("")
    public String galleryDetail(){
        return "galleryDetail";
    }
}
