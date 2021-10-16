package com.works.repositories;

import com.works.entities.OrderBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBoxRepository extends JpaRepository<OrderBox,Integer> {
}
