package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CategoryAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String news_categoryName;
}
