package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "NewsInterLayer", description = "Haber veri ekleme için kullanılır.")

public class NewsInterLayer {

    @NotEmpty(message = "Haber Başlığı boş olamaz")
    @NotNull(message = "Haber Başlığı null olamaz!")
    @ApiModelProperty(value = "Haber Başlığı ")
    private String newstitle;

    @NotEmpty(message = "Haber Açıklama boş  olamaz")
    @NotNull(message = "Haber Açıklama null olamaz!")
    @ApiModelProperty(value = "Haber Açıklama ")
    private String news_desc;

    @NotEmpty(message = "Haber Ayrıntılı açıklama boş olamaz")
    @NotNull(message = "Haber Ayrıntılı Açıklama null olamaz!")
    @ApiModelProperty(value = "Haber Ayrıntılı Açıklama ")
    private String news_detail_desc;

    @Min(value = 1)
    @ApiModelProperty(value = "Haber Durumu ")
    private Integer news_status;

    @Min(value = 1)
    @ApiModelProperty(value = "Haber Kategori ")
    private Integer news_category;

}
