package com.works.repositories;


import com.works.models.CategoryProductDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryProductDocRepository extends ElasticsearchRepository<CategoryProductDoc,String > {
    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"productcategoryname\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<CategoryProductDoc> findByTitle(String data, Pageable pageable);
}
