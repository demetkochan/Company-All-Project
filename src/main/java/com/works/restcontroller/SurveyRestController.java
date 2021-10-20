package com.works.restcontroller;

import com.works.dto.SurveyDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/survey_rest")
public class SurveyRestController {
    final SurveyDto sDto;

    public SurveyRestController(SurveyDto sDto) {
        this.sDto = sDto;
    }

}
