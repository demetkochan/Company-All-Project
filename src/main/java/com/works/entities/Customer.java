package com.works.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ApiModel(value = "Customer",description = "Müşteri ekleme için kullanılır")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ApiModelProperty(value = "Müşteri Adı ")
    private String cname;

    @ApiModelProperty(value = "Müşteri Soyadı ")
    private String csurname;

    @ApiModelProperty(value = "Müşteri email ")
    private String cemail;

    @ApiModelProperty(value = "Müşteri Telefon ")
    private String cphone;

    @ApiModelProperty(value = "Müşteri Durum ")
    private boolean status;

    private boolean enabled;
    private boolean tokenExpired;




}
