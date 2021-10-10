package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(" ")
public class DashboardController {

    @GetMapping("")
    public String dashboard(Model model){

        return "dashboard";
    }

}
