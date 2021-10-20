package com.works.repositories;


import com.works.models.CategoryGalleryDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryGalleryDocRepository extends ElasticsearchRepository<CategoryGalleryDoc,String> {
    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"gallerycategoryname\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<CategoryGalleryDoc> findByTitle(String data, Pageable pageable);
}
