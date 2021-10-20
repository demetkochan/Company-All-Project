package com.works.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "announcementdoc")
@Data
public class AnnouncementDoc {
    @Id
    private String id= UUID.randomUUID().toString();

    @Field(type= FieldType.Text)
    private String announcementtitle;


    @Field(type=FieldType.Text)
    private String announcement_detail_desc;


    @Field(type=FieldType.Text)
    private String  announcement_status;

    @Field(type=FieldType.Text)
    private String announcement_date;
}
