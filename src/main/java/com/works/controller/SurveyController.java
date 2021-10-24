package com.works.controller;


import com.works.entities.Survey;
import com.works.entities.SurveyOption;
import com.works.repositories.SurveyOptionRepository;
import com.works.repositories.SurveyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/survey_mvc")
public class SurveyController {
    final SurveyRepository sRepo;
    final SurveyOptionRepository soRepo;
    Integer searchSize;
    public SurveyController(SurveyRepository sRepo, SurveyOptionRepository soRepo) {
        this.sRepo = sRepo;
        this.soRepo = soRepo;
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



    @PostMapping("/optionAdd")
    public SurveyOption optadd(SurveyOption surveyOption){
       return soRepo.save(surveyOption);
    }

    @ResponseBody
    @GetMapping("/surveyList/{pageNumber}/{stPageSize}")
    public List<Survey> surveyList(@PathVariable String pageNumber, @PathVariable String stPageSize){

        int ipageNumber = Integer.parseInt(pageNumber);
        int pageSize = Integer.parseInt(stPageSize);

        if( pageSize == -1) {
            List<Survey> lst = new ArrayList<>();
            Iterable<Survey> page = sRepo.findAll();
            for (Survey item : page){
                lst.add(item);
            }
            Collections.reverse(lst);
            return lst;
        } else {
            Pageable pageable = PageRequest.of(ipageNumber, pageSize);
            Slice<Survey> pageList = sRepo.findByOrderByIdAsc(pageable);
            List<Survey> list = pageList.getContent();
            return list;
        }


    }
    @ResponseBody
    @GetMapping("/search/{pageNumber}/{stPageSize}/{data}")
    public List<Survey> surveysSearch(@PathVariable String data, @PathVariable int pageNumber, @PathVariable int stPageSize){

        Page<Survey> pages = sRepo.findBySurveytitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data, PageRequest.of(pageNumber, stPageSize));
        List<Survey> list = pages.getContent();
        List<Survey> listp = sRepo.findBySurveytitleContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listp.size();
        return list;

    }


    @ResponseBody
    @GetMapping("/List/pageCount/{stPageSize}/{stPageStatus}")
    public Integer surveypageCount(@PathVariable String stPageSize, @PathVariable String stPageStatus) {
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if (pageStatus == 1) {
            dataCount = sRepo.count();
        }
        else{
            dataCount = searchSize;

        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stPageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;
    }


}
