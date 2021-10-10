package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address_mvc")
public class AddressController {
    @GetMapping("")
    public String address(){
        return "address";
    }
}
