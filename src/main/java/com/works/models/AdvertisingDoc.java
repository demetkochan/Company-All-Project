package com.works.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "advertisingdoc")
@Data
public class AdvertisingDoc {
    @Id
    private String id= UUID.randomUUID().toString();

    @Field(type= FieldType.Text)
    private String advtitle;

    @Field(type=FieldType.Text)
    private String screentime;

    @Field(type=FieldType.Text)
    private String height;

    @Field(type=FieldType.Text)
    private String width;

    @Field(type=FieldType.Text)
    private String starttime;

    @Field(type=FieldType.Text)
    private String endtime;

    @Field(type=FieldType.Text)
    private String link;

    @Field(type=FieldType.Text)
    private String imageName;
}
