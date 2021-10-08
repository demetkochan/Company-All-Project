package com.works.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/advertising")
public class AdvertisingManagementController {
    @GetMapping("")
    public String advertising(){
        return "advertisingManagement";
    }
}
