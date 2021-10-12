package com.works.controller;

import com.works.services.UtilServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard_mvc")
public class HomeController {

    final UtilServices uservice;

    public HomeController(UtilServices uservice) {
        this.uservice = uservice;
    }

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("sumContent",uservice.countContent());
        model.addAttribute("sumNews",uservice.countNews());
        model.addAttribute("sumNewsActive",uservice.countNewsActive());
        model.addAttribute("sumNewsPassive",uservice.countNewsPassive());
        model.addAttribute("sumAnnouncement",uservice.countAnnouncement());
        model.addAttribute("sumProduct",uservice.countProduct());


        return "home";
    }

}
