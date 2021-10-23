package com.works.dto;

import com.works.entities.CategoryProduct;
import com.works.entities.ProductsImages;
import com.works.entities.Survey;
import com.works.entities.SurveyOption;
import com.works.repositories.SurveyOptionRepository;
import com.works.repositories.SurveyRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class SurveyDto {
    final SurveyRepository sRepo;
    final SurveyOptionRepository soRepo;
    final Util util;

    public SurveyDto(SurveyRepository sRepo, SurveyOptionRepository soRepo, Util util) {
        this.sRepo = sRepo;
        this.soRepo = soRepo;
        this.util = util;
    }

    //Anket Ekleme
    public Map<ERest, Object> Surveyadd(Survey survey, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Survey surveyadd = sRepo.save(survey);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, surveyadd);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }

        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }

    //Anket Silme
    public Map<ERest, Object>Surveydelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int cuid = Integer.parseInt(strIndex);
        try{
            if(sRepo.existsById(cuid)){
                sRepo.deleteById(cuid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, cuid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, cuid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, cuid);
        }
        return hm;
    }

    //Anket Güncelleme
    public Map<ERest, Object> SurveyUpdate(Survey survey, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( survey.getId() != null ) {

                Optional<Survey> oContent = sRepo.findById(survey.getId());
                if ( oContent.isPresent() ) {
                    try {
                        sRepo.saveAndFlush(survey);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, survey);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, survey);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, survey);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, survey);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }

    //Anketleri listeleme
    public Map<ERest,Object> surveyList(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Survey> ls = sRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Ankete seçenek ekleme
    public Map<ERest, Object> SurveyOptionadd(SurveyOption surveyOption, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                SurveyOption SurveyOptionadd = soRepo.save(surveyOption);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, SurveyOptionadd);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }

        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }

    //Seçili anketin seçeneklerini listeleme
    public Map<ERest,Object> surveyOptionList(String id){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int pid = Integer.parseInt(id);
        List<SurveyOption> ls = soRepo.surveyOptionList(pid);
        hm.put(ERest.result,ls);
        return hm;
    }

    //Anketlerin seçenek silme
    public Map<ERest, Object>SurveyOptionDelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int cuid = Integer.parseInt(strIndex);
        try{
            if(soRepo.existsById(cuid)){
                soRepo.deleteById(cuid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, cuid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, cuid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, cuid);
        }
        return hm;
    }






}
