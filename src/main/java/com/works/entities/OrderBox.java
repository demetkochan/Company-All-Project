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
@ApiModel(value = "OrderBox", description = "Sipariş veri ekleme için kullanılır.")

public class OrderBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid", nullable = false)
    private Integer id;

    @Min(1)
    @ApiModelProperty(value = "Sipariş edilen ürün id ")
    private int order_product;

    @Min(1)
    @ApiModelProperty(value = "Sipariş edilen ürün miktarı ")
    private int order_count;

    @ApiModelProperty(value = "Sipariş Tarihi ")
    private Date order_date;

    @ApiModelProperty(value = "Müşteri adresi ")
    @NotNull(message = "Duyuru detayı null olamaz")
    @NotEmpty(message = "Duyuru detayı boş olamaz")
    private String customer_address;

    @ApiModelProperty(value = "Sipariş Durumu ")
    private int order_status;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_customer" ,referencedColumnName="id")
    private Customer order_customer;

}
