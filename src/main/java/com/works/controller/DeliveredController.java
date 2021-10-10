package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delivered_mvc")
public class DeliveredController {

    @GetMapping("")
    public String delivered(Model model){
        return "delivered";
    }
}
