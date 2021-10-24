package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "SurveyOption", description = "Ankete Seçenek  ekleme için kullanılır.")

public class SurveyOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Min(1)
    @ApiModelProperty(value = "Seçenek eklenecek anket id ")
    private int survey_id;

    @NotNull(message = "Seçenek Başlığı null olamaz")
    @NotEmpty(message = "Seçenek Başlığı boş olamaz")
    @ApiModelProperty(value = "Seçenek Başlığı ")
    private String optiontitle;
    private int optioncount;




}
