package com.works.repositories;

import com.works.entities.Product;
import com.works.entities.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey,Integer> {
    @Query(value = "select COUNT(id)  as totalSurvey from SURVEY",nativeQuery = true)
    int countSurvey();

    Page<Survey> findByOrderByIdAsc(Pageable pageable);

    Page<Survey> findBySurveytitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String surveytitle, Pageable pageable);

    List<Survey> findBySurveytitleContainsIgnoreCaseAllIgnoreCase(String surveytitle);
}
