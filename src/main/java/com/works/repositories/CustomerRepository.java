package com.works.repositories;

import com.works.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select COUNT(id)  as totalCustomer from CUSTOMER",nativeQuery = true)
    int countCustomer();

}
