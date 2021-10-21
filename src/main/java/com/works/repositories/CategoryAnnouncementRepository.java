package com.works.repositories;

import com.works.entities.CategoryAnnouncement;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryAnnouncementRepository extends JpaRepository<CategoryAnnouncement,Integer> {
    Page<CategoryAnnouncement> findByOrderByIdDesc(Pageable pageable);

   // List<CategoryAnnouncement> findByNews_categoryNameAllIgnoreCase(String newscategoryName);

    List<CategoryAnnouncement> findByNewscategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String newscategoryname);

    Page<CategoryAnnouncement> findByNewscategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdDesc(String newscategoryname, Pageable pageable);


   List<CategoryAnnouncement> findByNewscategorynameContainsIgnoreCaseAllIgnoreCase(String newscategoryname);
}
