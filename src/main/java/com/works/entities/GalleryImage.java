package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GalleryImage {
    @Id
    @Column(name = "gid", nullable = false)
    private Integer id;

    private int g_id;

    private String gallery_image;

    private String image_desc;
}
