package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@ApiModel(value = "Content", description = "İçerik veri ekleme için kullanılır.")

public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "İçerik Başlığı ")
    @NotNull(message = "İçerik Başlığı Null Olamaz")
    @NotEmpty(message = "İçerik Başlığı Boş olamaz")
    private String contenttitle;

    @NotNull(message = "İçerik açıklama Null Olamaz")
    @NotEmpty(message = "İçerik açıklama Boş olamaz")
    @ApiModelProperty(value = "İçerik Açıklama ")
    private String content_desc;

    @Length(max = 1000)
    @Column(length = 1000)
    @ApiModelProperty(value = "İçerik Detaylı Açıklama ")
    private String content_detail_desc;

    @ApiModelProperty(value = "İçerik Tarihi ")
    private Date content_date;

    @ApiModelProperty(value = "içerik Durum ")
    private String content_status;






}
