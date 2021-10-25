package com.works.repositories;

import com.works.entities.Announcement;
import com.works.entities.Content;
import com.works.entities.News;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query(value = "select count(n_id) from NEWS",nativeQuery = true)
    int countNews();

    @Query(value = "select count(n_id) from NEWS as n WHERE n.news_status=1",nativeQuery = true)
    int countNewsActive();

    @Query(value = "select count(n_id) from NEWS as n WHERE n.news_status=2",nativeQuery = true)
    int countNewsPassive();

    @Query(value = " select * from NEWS where news_category = ?1 ",nativeQuery = true)
    List<News> process(int pr);


    @Query(value = "select * from NEWS where news_status = ?1",nativeQuery = true)
    List<News> statusproces(int a);

    Page<News> findByOrderByIdAsc(Pageable pageable);

    Page<News> findByNewstitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String newstitle, Pageable pageable);

    List<News> findByNewstitleContainsIgnoreCaseAllIgnoreCase(String newstitle);


}
