package com.works.entities;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AdvertisingInterLayer {

    @NotNull(message = "Başlık null olamaz!")
    @NotEmpty(message = "Başlık boş olamaz!")
    private String advtitle;
    @NotNull(message = "Gösterim sayısı null olamaz!")
    @NotEmpty(message = "Gösterim sayısı boş olamaz!")
    private String screentime;
    @NotNull(message = "Yükseklik null olamaz!")
    @NotEmpty(message = "Yükseklik boş olamaz!")
    private String height;
    @NotNull(message = "Genişlik null olamaz!")
    @NotEmpty(message = "Genişlik boş olamaz!")
    private String width;
    @NotNull(message = "Tarih  null olamaz!")
    @NotEmpty(message = "Tarih  boş olamaz!")
    private String starttime;
    @NotNull(message = "Tarih null olamaz!")
    @NotEmpty(message = "Tarih  boş olamaz!")
    private String endtime;
    @NotNull(message = "Link null olamaz!")
    @NotEmpty(message = "Link boş olamaz!")
    private String link;
}
