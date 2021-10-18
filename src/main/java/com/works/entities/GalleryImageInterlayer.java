package com.works.entities;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GalleryImageInterlayer {

    private int g_id;
    private String image_desc;
}
