package com.works.controller;

import com.works.entities.ProductsImages;
import com.works.entities.SurveyOption;
import com.works.repositories.SurveyOptionRepository;
import com.works.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/surveydetail_mvc")
public class SurveyDetailController {

    final SurveyRepository sRepo;
    final SurveyOptionRepository soRepo;

    public SurveyDetailController(SurveyRepository sRepo, SurveyOptionRepository soRepo) {
        this.sRepo = sRepo;
        this.soRepo = soRepo;
    }

    @GetMapping("")
    public String surveydetail(Model model){
        model.addAttribute("surveys",sRepo.findAll());

        return "surveyDetail";
    }


    @ResponseBody
    @PostMapping("/add")
    public SurveyOption add(@RequestBody SurveyOption surveyOption) {
        //jpa-----
        SurveyOption so = soRepo.save(surveyOption);
        return so;
    }

    @ResponseBody
    @GetMapping("/surveyOption/{stId}")
    public List<SurveyOption> productsImagesList(@PathVariable String stId){

        int id = Integer.parseInt(stId);
        List<SurveyOption> productsImagesList = soRepo.surveyOptionList(id);
        return productsImagesList;

    }



}
