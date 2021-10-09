package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {


    @GetMapping("")
    public String announcement(Model model){
        return "announcement";
    }

}
