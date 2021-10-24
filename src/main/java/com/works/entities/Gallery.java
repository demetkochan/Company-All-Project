package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Gallery", description = "Galeri veri ekleme için kullanılır.")

public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_gallery_id" ,referencedColumnName="id")
    private CategoryGallery categoryGallery;

    @ApiModelProperty(value = "Galeri Başlığı ")
    @NotNull(message = "Galeri Başlığı null olamaz")
    @NotEmpty(message = "Galeri Başlığı boş olamaz")
    private String galleryname;

    @ApiModelProperty(value = "Galeri Detay ")
    @NotNull(message = "Galeri Detay null olamaz")
    @NotEmpty(message = "Galeri Detay boş olamaz")
    private String gallery_detail;

    @ApiModelProperty(value = "Galeri Durum ")
    @Min(value = 0)
    private int gallery_status;
}
