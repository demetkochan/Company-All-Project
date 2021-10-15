package com.works.repositories;

import com.works.entities.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisingRepository extends JpaRepository<Advertising,Integer> {
    List<Advertising> findByAdvtitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String advtitle);
}
