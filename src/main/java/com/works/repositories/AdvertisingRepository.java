package com.works.repositories;

import com.works.entities.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisingRepository extends JpaRepository<Advertising,Integer> {
}
