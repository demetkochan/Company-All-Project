package com.works.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class NewsInterLayer {

    @NotEmpty(message = "Haber Başlığı boş olamaz")
    @NotNull(message = "Haber Başlığı null olamaz!")
    private String newstitle;

    @NotEmpty(message = "Haber Açıklama boş  olamaz")
    @NotNull(message = "Haber Açıklama null olamaz!")
    private String news_desc;

    @NotEmpty(message = "Haber Ayrıntılı açıklama boş olamaz")
    @NotNull(message = "Haber Ayrıntılı Açıklama null olamaz!")
    private String news_detail_desc;

    @Min(value = 1)
    private Integer news_status;
    @Min(value = 1)
    private Integer news_category;

}
