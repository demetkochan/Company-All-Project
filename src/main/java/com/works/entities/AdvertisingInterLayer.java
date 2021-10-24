package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "Advertising", description = "Reklam veri ekleme için kullanılır.")
public class AdvertisingInterLayer {

    @NotNull(message = "Başlık null olamaz!")
    @NotEmpty(message = "Başlık boş olamaz!")
    @ApiModelProperty(value = "Reklam Başlığı ")
    private String advtitle;

    @NotNull(message = "Gösterim sayısı null olamaz!")
    @NotEmpty(message = "Gösterim sayısı boş olamaz!")
    @ApiModelProperty(value = "Gösterim Sayısı ")
    private String screentime;

    @NotNull(message = "Yükseklik null olamaz!")
    @NotEmpty(message = "Yükseklik boş olamaz!")
    @ApiModelProperty(value = "Yükseklik ")
    private String height;

    @NotNull(message = "Genişlik null olamaz!")
    @NotEmpty(message = "Genişlik boş olamaz!")
    @ApiModelProperty(value = "Genişlik ")
    private String width;

    @NotNull(message = "Tarih  null olamaz!")
    @NotEmpty(message = "Tarih  boş olamaz!")
    @ApiModelProperty(value = "Başlangıç Tarihi ")

    private String starttime;
    @NotNull(message = "Tarih null olamaz!")
    @NotEmpty(message = "Tarih  boş olamaz!")
    @ApiModelProperty(value = "Bitiş Tarihi ")

    private String endtime;
    @NotNull(message = "Link null olamaz!")
    @NotEmpty(message = "Link boş olamaz!")
    @ApiModelProperty(value = "Reklam linki ")
    private String link;
}
