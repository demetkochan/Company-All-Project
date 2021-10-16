package com.works.controller;

import com.works.entities.OrderBox;
import com.works.repositories.OrderBoxRepository;
import com.works.services.UtilServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order_mvc")
public class OrderController {
    final UtilServices uService;
    final OrderBoxRepository obRepo;

    public OrderController(UtilServices uService, OrderBoxRepository obRepo) {
        this.uService = uService;
        this.obRepo = obRepo;
    }

    @GetMapping("")
    public String order(Model model){
        model.addAttribute("customer",uService.customerList());
        model.addAttribute("product",uService.productList());
        return "order";
    }

    @ResponseBody
    @PostMapping("/add")
    public OrderBox add(@RequestBody OrderBox orderBox){
        //jpa-----
        OrderBox o = obRepo.save(orderBox);
        return o;
    }

}