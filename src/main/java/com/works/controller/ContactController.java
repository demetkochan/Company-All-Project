package com.works.controller;

import com.works.entities.Contact;
import com.works.repositories.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
public class ContactController {

    final ContactRepository cRepo;

    public ContactController(ContactRepository cRepo) {
        this.cRepo = cRepo;
    }

    @GetMapping("")
    public String contact(){
        return "contact";
    }

    @ResponseBody
    @PostMapping("/add")
    public Contact add(@RequestBody Contact contact) {
        //jpa-----
        Contact a = cRepo.save(contact);
        return a;
    }



}
