package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/product_mvc")
public class ProductController {
    @GetMapping("")
    public String product(Model model){

        return "products";
    }
}
