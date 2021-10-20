package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer id;

    private String name;
    private String email;
    private String message;


}
