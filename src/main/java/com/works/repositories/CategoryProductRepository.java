package com.works.repositories;

import com.works.entities.CategoryProduct;
import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct,Integer> {
  //  List<CategoryProduct> findByProduct_categoryNameContainsAllIgnoreCase(String product_categoryName);

    List<CategoryProduct> findByProductcategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String productcategoryname);
}
