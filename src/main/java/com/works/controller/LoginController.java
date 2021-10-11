package com.works.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String Login(){
        return "login";
    }
    @RequestMapping(method = RequestMethod.POST)
    @PostMapping("")
    public String login(){
        return "login";
    }
}
