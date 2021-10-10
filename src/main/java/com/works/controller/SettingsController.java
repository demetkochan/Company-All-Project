package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings_mvc")
public class SettingsController {
    @GetMapping("")
    public String settings(){
        return "settings";
    }
}