package com.works.repositories;

import com.works.entities.Content;
import com.works.entities.News;
import com.works.entities.OrderBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface OrderBoxRepository extends JpaRepository<OrderBox,Integer> {

    @Query(value = " select p.productname,o.oid, c.cname, c.cemail, c.csurname, c.cphone, p.id, o.order_count, (p.product_price * o.order_count) as total, o.order_date from PRODUCT as p INNER JOIN order_box AS O ON p.id = o.order_product INNER JOIN Customer as c on c.id = o.order_customer ",nativeQuery = true)
    List<ProductJoinOrder> order();

    @Modifying
    @Query(value = " update order_box as o set o.order_status = 2 where o.oid=?1 ",nativeQuery = true)
    void orderStatus(int id);


    @Query(value = "select p.productname,o.oid, c.cname, c.cemail, c.csurname, c.cphone, p.id, o.order_count, (p.product_price * o.order_count) as total, o.order_date from PRODUCT as p INNER JOIN order_box AS O ON p.id = o.order_product INNER JOIN Customer as c on c.id = o.order_customer where o.order_status=1",nativeQuery = true)
    List<ProductJoinOrder> statusDelivered();


    @Query(value = "select p.productname,o.oid, c.cname, c.cemail, c.csurname, c.cphone, p.id, o.order_count, (p.product_price * o.order_count) as total, o.order_date from PRODUCT as p INNER JOIN order_box AS O ON p.id = o.order_product INNER JOIN Customer as c on c.id = o.order_customer where o.order_status=2",nativeQuery = true)
    List<ProductJoinOrder> status();

    @Modifying
    @Query(value = " update order_box as o set o.order_status = 1 where o.oid=?1 ",nativeQuery = true)
    void deliveredStatus(int id);

    @Query(value = "select COUNT(oid)  as totalOrder from order_box",nativeQuery = true)
    int countOrder();

}
