package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.internal.dynalink.linker.LinkerServices;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@ApiModel(value = "Survey", description = "Anket veri ekleme için kullanılır.")

public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Anket Başlığı null olamaz")
    @NotEmpty(message = "Anket Başlığı boş olamaz")
    @ApiModelProperty(value = "Anket Başlığı ")
    private String surveytitle;


}
