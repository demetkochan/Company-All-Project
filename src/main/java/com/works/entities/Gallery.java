package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_gallery_id" ,referencedColumnName="id")
    private CategoryGallery categoryGallery;

    private String galleryname;
    private String gallery_detail;
    private int gallery_status;
}
