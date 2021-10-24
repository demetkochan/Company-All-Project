package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@ApiModel(value = "Product", description = "Ürün veri ekleme için kullanılır.")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Ürün adı null olamaz")
    @NotEmpty(message = "Ürün adı boş olamaz")
    @ApiModelProperty(value = "Ürün Adı ")
    private String productname;

    @NotNull(message = "Ürün kısa açıklaması null olamaz")
    @NotEmpty(message = "Ürün kısa açıklaması boş olamaz")
    @ApiModelProperty(value = "Ürün Açıklama ")
    private String product_desc;

    @NotNull(message = "Ürün detaylı açıklaması null olamaz")
    @NotEmpty(message = "Ürün detaylı açıklaması boş olamaz")
    @ApiModelProperty(value = "Ürün Detaylı Açıklama ")
    private String product_detail;

    @Min(1)
    @ApiModelProperty(value = "Ürün Fiyatı ")
    private int product_price;

    @Min(1)
    @ApiModelProperty(value = "Ürün Tipi ")
    private int product_type;

    @Min(1)
    @ApiModelProperty(value = "Ürün Kampanya Durumu ")
    private int campaign;

    @NotNull(message = "Kampanya adı null olamaz")
    @NotEmpty(message = "Kampanya adı boş olamaz")
    @ApiModelProperty(value = "Kampanya Adı ")
    private String campaign_name;

    @NotNull(message = "Kampanya açıklaması null olamaz")
    @NotEmpty(message = "Kampanya açıklaması boş olamaz")
    @ApiModelProperty(value = "Kampanya Açıklama")
    private String campaign_desc;

    @NotNull(message = "Adres null olamaz")
    @NotEmpty(message = "Adres boş olamaz")
    @ApiModelProperty(value = "Adres ")
    private String address;

    @Min(0)
    @ApiModelProperty(value = "Enlem ")
    private long latitude;

    @Min(0)
    @ApiModelProperty(value = "Boylam ")
    private long longitude;

    @Min(0)
    private int productLİke;


    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "products_categories",joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "products_categories_id"))
    private List<CategoryProduct> categoryProducts;



}
