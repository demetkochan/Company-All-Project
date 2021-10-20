package com.works.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.UUID;

@Document(indexName = "contentdoc")
@Data
public class ContentDoc {
    @Id
    private String id= UUID.randomUUID().toString();

    @Field(type= FieldType.Text)
    private String contenttitle;

    @Field(type=FieldType.Text)
    private String content_desc;

    @Field(type=FieldType.Text)
    private String content_detail_desc;

    @Field(type=FieldType.Date)
    private Date content_date;

    @Field(type=FieldType.Text)
    private String content_status;
}
