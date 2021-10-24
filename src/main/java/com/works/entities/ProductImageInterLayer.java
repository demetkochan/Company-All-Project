package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@ApiModel(value = "ProductImageInterLayer", description = "Ürüne resim ekleme için kullanılır.")
public class ProductImageInterLayer {

    @Min(1)
    @ApiModelProperty(value = "Resim eklenecek ürün id ")
    private int product_id;
}
