package com.works.controller;

import com.works.services.UtilServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard_mvc")
public class HomeController {

    final UtilServices utilServices;

    public HomeController(UtilServices utilServices) {
        this.utilServices = utilServices;
    }

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("sumContent", utilServices.countContent());
        model.addAttribute("sumNews", utilServices.countNews());
        model.addAttribute("sumNewsActive", utilServices.countNewsActive());
        model.addAttribute("sumNewsPassive", utilServices.countNewsPassive());
        model.addAttribute("sumAnnouncement", utilServices.countAnnouncement());
        model.addAttribute("sumProduct", utilServices.countProduct());
        model.addAttribute("sumCustomer", utilServices.countCustomer());
        model.addAttribute("countSurvey", utilServices.countSurvey());
        model.addAttribute("countOrder", utilServices.countOrder());
        return "home";
    }

}
