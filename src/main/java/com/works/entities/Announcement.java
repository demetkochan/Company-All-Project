package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String announcement_title;
    private String announcement_detail_desc;
    private int announcement_status;
    private Date announcement_date;

}
