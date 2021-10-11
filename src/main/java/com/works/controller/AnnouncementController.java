package com.works.controller;

import com.works.entities.Announcement;
import com.works.entities.Content;
import com.works.repositories.AnnouncementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/announcement_mvc")
public class AnnouncementController {

    final AnnouncementRepository aRepo;

    public AnnouncementController(AnnouncementRepository aRepo) {
        this.aRepo = aRepo;
    }

    @GetMapping("")
    public String announcement(Model model){
        return "announcement";
    }


    //Duyuru ekleme
    @ResponseBody
    @PostMapping("/add")
    public Announcement add(@RequestBody Announcement announcement){
        //jpa-----
        Announcement a = aRepo.save(announcement);
        return a;
    }
    //Duyuru Listeleme
    @ResponseBody
    @GetMapping("/list")
    public List<Announcement> list(){
        return aRepo.findAll();
    }




}
