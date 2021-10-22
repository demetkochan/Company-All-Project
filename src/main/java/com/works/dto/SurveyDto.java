package com.works.dto;

import com.works.entities.Content;
import com.works.entities.Survey;
import com.works.repositories.SurveyRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SurveyDto {
    final SurveyRepository sRepo;
    final Util util;

    public SurveyDto(SurveyRepository sRepo, Util util) {
        this.sRepo = sRepo;
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

}
