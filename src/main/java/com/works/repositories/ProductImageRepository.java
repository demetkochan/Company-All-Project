package com.works.repositories;

import com.works.entities.ProductImage;

import com.works.entities.ProductsImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    @Query(value = "SELECT pro.PRODUCTNAME,pro.ID, p.PRODUCT_IMAGE, p.P_ID from PRODUCT_IMAGE as p INNER JOIN PRODUCT as pro on pro.ID=p.PRODUCT_ID where pro.ID = ?1", nativeQuery = true)
    List<ProductsImages> productsImagesList(int id);
}
