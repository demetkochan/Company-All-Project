package com.works.repositories;

import com.works.entities.Advertising;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisingRepository extends JpaRepository<Advertising,Integer> {

    Page<Advertising> findByOrderByIdAsc(Pageable pageable);

    Page<Advertising> findByAdvtitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String advtitle, Pageable pageable);

    List<Advertising> findByAdvtitleContainsIgnoreCaseAllIgnoreCase(String advtitle);
}