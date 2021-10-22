package com.works.controller;

import com.works.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings_mvc")
public class SettingsController {
    @GetMapping("")
    public String settings(){
        return "settings";
    }




}