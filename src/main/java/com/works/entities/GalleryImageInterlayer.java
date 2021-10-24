package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "GalleryImageInterlayer", description = "Galeri Resim ekleme için kullanılır.")

public class GalleryImageInterlayer {

    @ApiModelProperty(value = "Galeri id")
    @Min(value = 0)
    private int g_id;

    @ApiModelProperty(value = "Resim Açıklama ")
    @NotNull(message = "Galeri Detay null olamaz")
    @NotEmpty(message = "Galeri Detay boş olamaz")
    private String image_desc;
}
