package com.works.repositories;

import com.works.entities.Announcement;
import com.works.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

    @Query(value = "select count(id) from ANNOUNCEMENT  ",nativeQuery = true)
    int countAnnouncement();

    @Query(value = " select * from ANNOUNCEMENT where announcement_status = ?1 ",nativeQuery = true)
    List<Announcement> process(int a);

}
