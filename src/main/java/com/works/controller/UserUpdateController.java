package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userUpdate_mvc")
public class UserUpdateController {
    @GetMapping("")
    public String userUpdate(){
        return "userUpdate";
    }
}
