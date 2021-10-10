package com.works.repositories;

import com.works.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Integer> {

    @Query(value = " select * from CONTENT where CONTENT_STATUS = ?1 ",nativeQuery = true)
    List<Content> process(int pr);
}
