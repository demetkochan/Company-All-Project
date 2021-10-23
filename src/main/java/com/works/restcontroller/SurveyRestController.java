package com.works.restcontroller;

import com.works.dto.SurveyDto;
import com.works.entities.Survey;
import com.works.entities.SurveyOption;
import com.works.repositories.SurveyOptionRepository;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/survey_rest")
public class SurveyRestController {
    final SurveyDto sDto;
    final SurveyOptionRepository soRepo;

    public SurveyRestController(SurveyDto sDto, SurveyOptionRepository soRepo) {
        this.sDto = sDto;
        this.soRepo = soRepo;
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

    //Anketleri listeleme
    @GetMapping("/surveyList")
    public Map<ERest ,Object> surveyList(){
        return sDto.surveyList();
    }

    //Ankete seçenek ekle
    @PostMapping("/surveyOptionadd")
    public Map<ERest, Object> surveyOptionadd(@RequestBody @Valid SurveyOption surveyOption, BindingResult bResult){
        return sDto.SurveyOptionadd(surveyOption,bResult);
    }

    //Seçili anketin seçeneklerini listele
    @GetMapping("/surveyOptionList/{strIndex}")
    public Map<ERest ,Object> surveyOptionList(@PathVariable String strIndex){
        return sDto.surveyOptionList(strIndex);
    }

    //Seçili anketin seçeneğini silme
    @DeleteMapping("/surveyOptiondelete/{strIndex}")
    public Map<ERest, Object> surveyOptiondelete(@PathVariable String strIndex){
        return sDto.SurveyOptionDelete(strIndex);
    }

    //Seçili ankete oy verme
    @PutMapping("/count/{stId}")
    public void count(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        soRepo.optionCount(cid);
    }




}
