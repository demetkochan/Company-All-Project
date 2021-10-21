package com.works.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Document(indexName = "productdoc")
@Data
public class ProductDoc {
    @Id
    private String id= UUID.randomUUID().toString();

    @Field(type= FieldType.Text)
    private String productname;

    @Field(type= FieldType.Text)
    private String product_desc;

    @Field(type= FieldType.Text)
    private String product_detail;

    @Field(type= FieldType.Text)
    private int product_price;

    @Field(type= FieldType.Text)
    private int product_type;

    @Field(type= FieldType.Text)
    private int campaign;

    @Field(type= FieldType.Text)
    private String campaign_name;

    @Field(type= FieldType.Text)
    private String campaign_desc;

    @Field(type= FieldType.Text)
    private String address;

    @Field(type= FieldType.Long)
    private long latitude;

    @Field(type= FieldType.Long)
    private long longitude;

    @Field(type= FieldType.Text)
    private int productLİke;
}
