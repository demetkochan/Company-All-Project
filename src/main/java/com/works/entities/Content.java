package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @NotNull(message = "İçerik Başlığı Null Olamaz")
    @NotEmpty(message = "İçerik Başlığı Boş olamaz")
    private String content_title;

    @NotNull(message = "İçerik açıklama Null Olamaz")
    @NotEmpty(message = "İçerik açıklama Boş olamaz")
    private String content_desc;
    @Length(max = 1000)
    @Column(length = 1000)
    private String content_detail_desc;
    private Date content_date;
    private int content_status;






}
