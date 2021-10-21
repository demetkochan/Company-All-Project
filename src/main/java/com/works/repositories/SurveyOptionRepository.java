package com.works.repositories;

import com.works.entities.ProductsImages;
import com.works.entities.SurveyOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyOptionRepository extends JpaRepository<SurveyOption,Integer> {

    /*
    @Query(value = "select optiontitle,vote from survey_option,survey where survey.id=?1",nativeQuery = true)
    List<SurveyOption> optList(int id);

     */

    @Query(value = "select * from survey_option as so where so.survey_id = ?1", nativeQuery = true)
    List<SurveyOption> surveyOptionList(int id);

}
