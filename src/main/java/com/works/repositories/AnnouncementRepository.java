package com.works.repositories;

import com.works.entities.Announcement;
import com.works.entities.News;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

    @Query(value = "select count(id) from ANNOUNCEMENT  ",nativeQuery = true)
    int countAnnouncement();

    @Query(value = " select * from ANNOUNCEMENT where announcement_status = ?1 ",nativeQuery = true)
    List<Announcement> process(int a);
    Page<Announcement> findByOrderByIdAsc(Pageable pageable);

    Page<Announcement> findByAnnouncementtitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String announcementtitle, Pageable pageable);

    List<Announcement> findByAnnouncementtitleContainsIgnoreCaseAllIgnoreCase(String announcementtitle);

}
