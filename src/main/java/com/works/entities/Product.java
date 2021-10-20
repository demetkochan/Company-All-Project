package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull(message = "Ürün adı null olamaz")
    @NotEmpty(message = "Ürün adı boş olamaz")
    private String productname;
    @NotNull(message = "Ürün kısa açıklaması null olamaz")
    @NotEmpty(message = "Ürün kısa açıklaması boş olamaz")
    private String product_desc;
    @NotNull(message = "Ürün detaylı açıklaması null olamaz")
    @NotEmpty(message = "Ürün detaylı açıklaması boş olamaz")
    private String product_detail;
    @Min(1)
    private int product_price;
    @Min(1)
    private int product_type;
    @Min(1)
    private int campaign;
    @NotNull(message = "Kampanya adı null olamaz")
    @NotEmpty(message = "Kampanya adı boş olamaz")
    private String campaign_name;
    @NotNull(message = "Kampanya açıklaması null olamaz")
    @NotEmpty(message = "Kampanya açıklaması boş olamaz")
    private String campaign_desc;
    @NotNull(message = "Adres null olamaz")
    @NotEmpty(message = "Adres boş olamaz")
    private String address;
    @Min(0)
    private long latitude;
    @Min(0)
    private long longitude;
    @Min(0)
    private int productLİke;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_product_id" ,referencedColumnName="id")
    private CategoryProduct categoryProduct;


}
