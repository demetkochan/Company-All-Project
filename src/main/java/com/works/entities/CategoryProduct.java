package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "CategoryProduct", description = "Ürün Kategoriye veri ekleme için kullanılır.")

public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Kategori adı Null Olamaz")
    @NotEmpty(message = "Kategori adı Boş olamaz")
    @ApiModelProperty(value = "Ürün Kategori ")
    private String productcategoryname;

}
