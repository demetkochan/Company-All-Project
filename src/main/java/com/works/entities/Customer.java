package com.works.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String cname;
    private String csurname;
    private String cemail;
    private String cphone;
    private boolean status;
    private boolean enabled;
    private boolean tokenExpired;




}
