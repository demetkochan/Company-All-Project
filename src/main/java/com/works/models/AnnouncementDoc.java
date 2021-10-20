package com.works.models;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
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

    @Min(value = 1)
    @Field(type=FieldType.Text)
    private String  announcement_status;

    @Field(type=FieldType.Date)
    private Date announcement_date;
}
