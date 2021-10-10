package com.works.repositories;

import com.works.entities.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct,Integer> {
}
