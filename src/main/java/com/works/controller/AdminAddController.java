package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_mvc")
public class AdminAddController {
    @GetMapping("")
    public String adminAdd(){
        return "adminAdd";
    }
}
