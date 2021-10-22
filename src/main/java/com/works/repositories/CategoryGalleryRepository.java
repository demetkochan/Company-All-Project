package com.works.repositories;

import com.works.entities.CategoryGallery;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryGalleryRepository extends JpaRepository<CategoryGallery,Integer> {
    List<CategoryGallery> findByGallerycategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String gallerycategoryname);

    Page<CategoryGallery> findByOrderByIdDesc(Pageable pageable);

    Page<CategoryGallery> findByGallerycategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdDesc(String gallerycategoryname, Pageable pageable);

    List<CategoryGallery> findByGallerycategorynameContainsIgnoreCaseAllIgnoreCase(String gallerycategoryname);
}
