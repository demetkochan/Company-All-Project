package com.works.restcontroller;

import com.works.dto.SurveyDto;
import com.works.entities.Content;
import com.works.entities.Survey;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/survey_rest")
public class SurveyRestController {
    final SurveyDto sDto;

    public SurveyRestController(SurveyDto sDto) {
        this.sDto = sDto;
    }


    //Anket Ekleme
    @PostMapping("/surveyadd")
    public Map<ERest, Object> add(@RequestBody @Valid Survey survey, BindingResult bResult){
        return sDto.Surveyadd(survey,bResult);
    }

    //Anket Silme
    @DeleteMapping("/surveydelete/{strIndex}")
    public Map<ERest, Object> surveydelete(@PathVariable String strIndex){
        return sDto.Surveydelete(strIndex);
    }

    //Anket Düzenleme
    @PutMapping("/surveyupdate")
    public Map<ERest, Object> surveyupdate(@RequestBody @Valid Survey survey, BindingResult bindingResult) {
        return sDto.SurveyUpdate(survey,bindingResult);
    }

}
