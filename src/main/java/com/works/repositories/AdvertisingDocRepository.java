package com.works.repositories;

import com.works.models.AdvertisingDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AdvertisingDocRepository extends ElasticsearchRepository<AdvertisingDoc,String> {
    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"advtitle\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<AdvertisingDoc> findByTitle(String data, Pageable pageable);
}
