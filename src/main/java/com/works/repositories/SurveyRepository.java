package com.works.repositories;

import com.works.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SurveyRepository extends JpaRepository<Survey,Integer> {
    @Query(value = "select COUNT(id)  as totalSurvey from SURVEY",nativeQuery = true)
    int countSurvey();
}
