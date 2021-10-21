package com.works.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "newsdoc")
@Data
public class NewsDoc {
    @Id
    private String id= UUID.randomUUID().toString();

    @Field(type= FieldType.Text)
    private String newstitle;

    @Field(type= FieldType.Text)
    private String news_desc;

    @Field(type= FieldType.Text)
    private String news_detail_desc;

    @Field(type= FieldType.Text)
    private int news_status;

    @Field(type= FieldType.Text)
    private int news_category;
}
