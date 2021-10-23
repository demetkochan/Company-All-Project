package com.works.repositories;

import com.works.entities.Customer;
import com.works.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select COUNT(id)  as totalCustomer from CUSTOMER",nativeQuery = true)
    int countCustomer();

    Page<Customer> findByOrderByIdAsc(Pageable pageable);

    Page<Customer> findByCnameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(String cname, Pageable pageable);

    List<Customer> findByCnameContainsIgnoreCaseAllIgnoreCase(String cname);

}
