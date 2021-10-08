package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {


    @GetMapping("/content")
    public String vendor(Model model){
        return "content";
    }


}
