package com.works.controller;

import com.works.repositories.OrderBoxRepository;
import com.works.repositories.ProductJoinOrder;
import com.works.services.UtilServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/delivered_mvc")
public class DeliveredController {

    final UtilServices uService;
    final OrderBoxRepository obRepo;

    public DeliveredController(UtilServices uService, OrderBoxRepository obRepo) {
        this.uService = uService;
        this.obRepo = obRepo;
    }

    @GetMapping("")
    public String delivered(Model model){
        return "delivered";
    }


    @ResponseBody
    @GetMapping("/statuslist")
    public List<ProductJoinOrder> orderStatusList(Model model){
        List<ProductJoinOrder> order = obRepo.status();
        model.addAttribute("total",order);
        return order;

    }

    @ResponseBody
    @PutMapping("/update/{stId}")
    public void orderBox(@PathVariable String stId) {
        //jpa-----
        int oid = Integer.parseInt(stId);
        obRepo.deliveredStatus(oid);
    }



}
