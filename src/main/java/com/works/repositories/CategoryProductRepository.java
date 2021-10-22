package com.works.repositories;

import com.works.entities.CategoryGallery;
import com.works.entities.CategoryProduct;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct,Integer> {
    Page<CategoryProduct> findByProductcategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdDesc(String productcategoryname, Pageable pageable);

    List<CategoryProduct> findByProductcategorynameContainsIgnoreCaseAllIgnoreCase(String productcategoryname);

    Page<CategoryProduct> findByOrderByIdDesc(Pageable pageable);
}
