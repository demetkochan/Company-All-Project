package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class OrderBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid", nullable = false)
    private Integer id;

    private int order_product;
    private int order_count;
    private Date order_date;
    private String customer_address;
    private int order_status;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_customer" ,referencedColumnName="id")
    private Customer order_customer;

}
