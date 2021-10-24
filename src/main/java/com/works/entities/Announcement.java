package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@ApiModel(value = "Announcement", description = "Duyuru veri ekleme için kullanılır.")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Duyuru Başlığı null olamaz")
    @NotEmpty(message = "Duyuru Başlığı boş olamaz")
    @ApiModelProperty(value = "Duyuru Başlığı ")
    private String announcementtitle;

    @NotNull(message = "Duyuru detayı null olamaz")
    @NotEmpty(message = "Duyuru detayı boş olamaz")
    @ApiModelProperty(value = "Duyuru Açıklama ")
    private String announcement_detail_desc;

    @Min(value = 1)
    @ApiModelProperty(value = "Duyuru Durumu ")
    private int announcement_status;

    @ApiModelProperty(value = "Duyuru Tarihi ")
    private Date announcement_date;

}
