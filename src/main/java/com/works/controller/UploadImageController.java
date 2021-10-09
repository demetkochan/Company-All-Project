package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/uploadImage")
public class UploadImageController {
    @GetMapping("")
    public String uploadImage(){
        return "uploadImage";
    }
}