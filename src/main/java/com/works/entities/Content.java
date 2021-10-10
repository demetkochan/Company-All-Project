package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String content_title;
    private String content_desc;
    @Length(max = 1000)
    @Column(length = 1000)
    private String content_detail_desc;
    private Date content_date;
    private int content_status;






}
