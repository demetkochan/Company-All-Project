package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Advertising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String advTitle;
    private String screentime;
    private String height;
    private String width;
    private String starttime;
    private String endtime;
    private String link;
    private String imageName;
}
