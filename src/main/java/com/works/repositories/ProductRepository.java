package com.works.repositories;

import com.works.entities.CategoryProduct;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select COUNT(id)  as totalProduct from PRODUCT",nativeQuery = true)
    int countProduct();


    @Query(value = "select  count(id) from product as p where p.`productli̇ke` =1",nativeQuery = true)
    int countLikeProduct();

    @Modifying
    @Query(value = " update product as p set  p.`productli̇ke` = 1 where p.id=?1 ",nativeQuery = true)
    void likeStatus(int id);


    @Modifying
    @Query(value = " update product as p set  p.`productli̇ke` = 0 where p.id=?1 ",nativeQuery = true)
    void dislikeStatus(int id);


    @Query(value = "select * from product as p where p.`productli̇ke` =1",nativeQuery = true)
    List<Product> likeResult();

    @Query(value = "select * from product as p where p.`productli̇ke` =0",nativeQuery = true)
    List<Product> dislikeResult();


    Page<Product> findByOrderByIdAsc(Pageable pageable);

    Page<Product> findByProductnameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String productname, Pageable pageable);

    List<Product> findByProductnameContainsIgnoreCaseAllIgnoreCase(String productname);


}
