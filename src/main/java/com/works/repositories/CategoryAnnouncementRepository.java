package com.works.repositories;

import com.works.entities.CategoryAnnouncement;
import com.works.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryAnnouncementRepository extends JpaRepository<CategoryAnnouncement,Integer> {
    List<CategoryAnnouncement> findByOrderByIdAsc(Pageable pageable);

   // List<CategoryAnnouncement> findByNews_categoryNameAllIgnoreCase(String newscategoryName);

    List<CategoryAnnouncement> findByNewscategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String newscategoryname);



}
