package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class OrderBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private int order_customer;
    private int order_product;
    private int order_count;
    private Date order_date;
    private String customer_address;
    private String order_email;
    private int order_status;
}
