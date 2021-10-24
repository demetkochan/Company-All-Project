package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "CategoryAnnouncement", description = "Duyuru Kategoriye veri ekleme için kullanılır.")
public class CategoryAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Kategori adı Null Olamaz")
    @NotEmpty(message = "Kategori adı Boş olamaz")
    @ApiModelProperty(value = "Duyuru Kategori ")
    private String newscategoryname;
}
