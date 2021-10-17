package com.works.dto;

import com.works.repositories.SurveyRepository;
import com.works.util.Util;
import org.springframework.stereotype.Service;

@Service
public class SurveyDto {
    final SurveyRepository sRepo;
    final Util util;

    public SurveyDto(SurveyRepository sRepo, Util util) {
        this.sRepo = sRepo;
        this.util = util;
    }
}
