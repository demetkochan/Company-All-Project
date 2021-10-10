package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order_mvc")
public class OrderController {

    @GetMapping("")
    public String order(Model model){
        return "order";
    }
}