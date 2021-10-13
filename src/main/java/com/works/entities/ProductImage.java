package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    private Integer id;

    private int product_id;
    private String product_image;


}
