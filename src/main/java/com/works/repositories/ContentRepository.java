package com.works.repositories;

import com.works.entities.Content;
import com.works.entities.Customer;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Integer> {

    @Query(value = " select * from CONTENT where CONTENT_STATUS = ?1 ",nativeQuery = true)
    List<Content> process(int pr);

    @Query(value = "select count(id) from CONTENT",nativeQuery = true)
    int countContent();
    Page<Content> findByOrderByIdAsc(Pageable pageable);
    List<Content> findByContenttitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String contenttitle);
}
