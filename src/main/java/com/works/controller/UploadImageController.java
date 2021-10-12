package com.works.controller;

import com.works.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/uploadImage_mvc")
public class UploadImageController {
    final ProductRepository pRepo;

    public UploadImageController(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    @GetMapping("")
    public String uploadImage(Model model){
        model.addAttribute("products",pRepo.findAll());
        return "uploadImage";
    }
}
