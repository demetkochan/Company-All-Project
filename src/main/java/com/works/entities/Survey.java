package com.works.entities;

import jdk.internal.dynalink.linker.LinkerServices;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String surveytitle;


}
