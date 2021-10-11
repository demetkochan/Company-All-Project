package com.works.repositories;

import com.works.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
}
