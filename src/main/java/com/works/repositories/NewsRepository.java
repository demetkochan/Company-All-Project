package com.works.repositories;

import com.works.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query(value = "select count(n_id) from NEWS",nativeQuery = true)
    int countNews();

    @Query(value = "select count(n_id) from NEWS as n WHERE n.news_status=1",nativeQuery = true)
    int countNewsActive();

    @Query(value = "select count(n_id) from NEWS as n WHERE n.news_status=2",nativeQuery = true)
    int countNewsPassive();





}
