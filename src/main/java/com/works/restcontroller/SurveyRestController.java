package com.works.restcontroller;

import com.works.dto.SurveyDto;
import com.works.entities.Survey;
import com.works.entities.SurveyOption;
import com.works.repositories.SurveyOptionRepository;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/survey_rest")
@Api(value ="SurveyRestController",authorizations ={@Authorization(value = "basicAuth")})

public class SurveyRestController {
    final SurveyDto sDto;
    final SurveyOptionRepository soRepo;

    public SurveyRestController(SurveyDto sDto, SurveyOptionRepository soRepo) {
        this.sDto = sDto;
        this.soRepo = soRepo;
    }


    //Anket Ekleme
    @ApiOperation("Anket veri ekleme")
    @PostMapping("/surveyadd")
    public Map<ERest, Object> add(@RequestBody @Valid Survey survey, BindingResult bResult){
        return sDto.Surveyadd(survey,bResult);
    }

    //Anket Silme
    @ApiOperation("Anket veri silme")
    @DeleteMapping("/surveydelete/{strIndex}")
    public Map<ERest, Object> surveydelete(@PathVariable String strIndex){
        return sDto.Surveydelete(strIndex);
    }

    //Anket Düzenleme
    @ApiOperation("Anket veri düzenleme")
    @PutMapping("/surveyupdate")
    public Map<ERest, Object> surveyupdate(@RequestBody @Valid Survey survey, BindingResult bindingResult) {
        return sDto.SurveyUpdate(survey,bindingResult);
    }

    //Anketleri listeleme
    @ApiOperation("Anket veri listeleme")
    @GetMapping("/surveyList")
    public Map<ERest ,Object> surveyList(){
        return sDto.surveyList();
    }

    //Ankete seçenek ekle
    @ApiOperation("Ankete seçenek ekleme")
    @PostMapping("/surveyOptionadd")
    public Map<ERest, Object> surveyOptionadd(@RequestBody @Valid SurveyOption surveyOption, BindingResult bResult){
        return sDto.SurveyOptionadd(surveyOption,bResult);
    }

    //Seçili anketin seçeneklerini listele
    @ApiOperation("Seçili anketin seçeneklerini listeleme")
    @GetMapping("/surveyOptionList/{strIndex}")
    public Map<ERest ,Object> surveyOptionList(@PathVariable String strIndex){
        return sDto.surveyOptionList(strIndex);
    }

    //Seçili anketin seçeneğini silme
    @ApiOperation("Seçili anketin seçeneklerini silme")
    @DeleteMapping("/surveyOptiondelete/{strIndex}")
    public Map<ERest, Object> surveyOptiondelete(@PathVariable String strIndex){
        return sDto.SurveyOptionDelete(strIndex);
    }

    //Seçili ankete oy verme
    @ApiOperation("Seçili ankete oy verme")
    @PutMapping("/count/{stId}")
    public void count(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        soRepo.optionCount(cid);
    }




}
