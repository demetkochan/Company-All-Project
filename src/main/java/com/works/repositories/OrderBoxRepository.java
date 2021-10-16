package com.works.repositories;

import com.works.entities.Content;
import com.works.entities.OrderBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderBoxRepository extends JpaRepository<OrderBox,Integer> {

    @Query(value = " select p.productname,o.oid, c.cname, c.cemail, c.csurname, c.cphone, p.id, o.order_count, (p.product_price * o.order_count) as total, o.order_date from PRODUCT as p INNER JOIN order_box AS O ON p.id = o.order_product INNER JOIN Customer as c on c.id = o.order_customer ",nativeQuery = true)
    List<ProductJoinOrder> order();


}
