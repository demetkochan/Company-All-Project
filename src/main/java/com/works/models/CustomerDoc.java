package com.works.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "customerdoc")
@Data
public class CustomerDoc {
    @Id
    private String id= UUID.randomUUID().toString();

    @Field(type= FieldType.Text)
    private String cname;
    @Field(type= FieldType.Text)
    private String csurname;
    @Field(type= FieldType.Text)
    private String cemail;
    @Field(type= FieldType.Text)
    private String cphone;
    @Field(type= FieldType.Boolean)
    private boolean status;
    @Field(type= FieldType.Boolean)
    private boolean enabled;
    @Field(type= FieldType.Boolean)
    private boolean tokenExpired;
    
}
