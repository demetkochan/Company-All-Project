package com.works.controller;

import com.works.entities.CategoryAnnouncement;
import com.works.repositories.CategoryAnnouncementRepository;
import com.works.repositories.CategoryGalleryRepository;
import com.works.repositories.CategoryProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    final CategoryAnnouncementRepository caRepo;
    final CategoryProductRepository cpRepo;
    final CategoryGalleryRepository cgRepo;

    public CategoryController(CategoryAnnouncementRepository caRepo, CategoryProductRepository cpRepo, CategoryGalleryRepository cgRepo) {
        this.caRepo = caRepo;
        this.cpRepo = cpRepo;
        this.cgRepo = cgRepo;
    }

    @GetMapping("")
    public String category(Model model){
        return "category";
    }

    @ResponseBody
    @PostMapping("/announcementAdd")
    public CategoryAnnouncement add(@RequestBody CategoryAnnouncement announcement){
        CategoryAnnouncement ca=caRepo.save(announcement);
        return ca;
    }


}
