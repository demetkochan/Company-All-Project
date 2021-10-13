package com.works.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class News extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_id", nullable = false)
    private Integer id;
    private String news_title;
    private String news_desc;
    private String news_detail_desc;
    private String news_image;
    private int news_status;
    private int news_category;
}
