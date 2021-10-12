package com.works.repositories;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select COUNT(id)  as totalProduct from PRODUCT",nativeQuery = true)
    int countProduct();
}
