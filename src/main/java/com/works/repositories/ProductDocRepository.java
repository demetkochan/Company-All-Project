package com.works.repositories;


import com.works.models.ProductDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc,String> {
    @Query("{\"bool\":{\"must\":[{\"match\":{\"productname\":\"?0\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<ProductDoc> findByTitle(String data, Pageable pageable);
}
