package com.works.controller;

import com.works.services.UtilServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order_mvc")
public class OrderController {
    final UtilServices uService;

    public OrderController(UtilServices uService) {
        this.uService = uService;
    }

    @GetMapping("")
    public String order(Model model){
        model.addAttribute("customer",uService.customerList());
        model.addAttribute("product",uService.productList());
        return "order";
    }
}