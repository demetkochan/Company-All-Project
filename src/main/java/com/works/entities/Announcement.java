package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Duyuru Başlığı null olamaz")
    @NotEmpty(message = "Duyuru Başlığı boş olamaz")
    private String announcementtitle;

    @NotNull(message = "Duyuru detayı null olamaz")
    @NotEmpty(message = "Duyuru detayı boş olamaz")
    private String announcement_detail_desc;

    @Min(value = 1)
    private int announcement_status;
    private Date announcement_date;

}
