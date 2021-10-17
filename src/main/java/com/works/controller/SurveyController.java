package com.works.controller;

import com.works.entities.CategoryAnnouncement;
import com.works.entities.CategoryProduct;
import com.works.entities.Survey;
import com.works.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/survey_mvc")
public class SurveyController {
    final SurveyRepository sRepo;

    public SurveyController(SurveyRepository sRepo) {
        this.sRepo = sRepo;
    }

    @GetMapping("")
    public String survey(){
        return "survey";
    }
    Survey surveyUpdate=new Survey();
    @ResponseBody
    @PostMapping("/add")
    public Survey add(@RequestBody Survey survey){
        try{
            if(surveyUpdate.getId() != null && surveyUpdate.getId() > 0){
                survey.setId(surveyUpdate.getId());
            }
            sRepo.saveAndFlush(survey);
            surveyUpdate = new Survey();

        }catch (Exception ex){

            System.err.println("İşlem sırasında hata oluştur!");
        }

        return surveyUpdate;

    }

    @ResponseBody
    @GetMapping("/surveyList")
    public List<Survey> list(){
        return sRepo.findAll();
    }

    @ResponseBody
    @DeleteMapping(value = "/surveyDelete/{stId}")
    public String delete(@PathVariable String stId) {
        String status = "0";
        try{
            int id = Integer.parseInt(stId);
            sRepo.deleteById(id);
            status= "1";

        }catch (Exception e){

            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }
}
