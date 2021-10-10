package com.works.controller;

import com.works.entities.Content;
import com.works.repositories.ContentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/content")
public class ContentController {

    final ContentRepository cRepo;

    public ContentController(ContentRepository cRepo) {
        this.cRepo = cRepo;
    }


    @GetMapping("")
    public String content(Model model){
        return "content";
    }


    @ResponseBody
    @PostMapping("/add")
    public Content contentAdd(@RequestBody Content content){
        Content c = cRepo.save(content);
        return c;

    }


}
