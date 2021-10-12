package com.works.repositories;

import com.works.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

    @Query(value = "select count(id) from ANNOUNCEMENT  ",nativeQuery = true)
    int countAnnouncement();
}
