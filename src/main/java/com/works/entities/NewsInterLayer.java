package com.works.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.util.Date;

@Data
public class NewsInterLayer {

    private String news_title;
    private String news_desc;
    private String news_detail_desc;
    private Integer news_status;
    private Integer news_category;

}
