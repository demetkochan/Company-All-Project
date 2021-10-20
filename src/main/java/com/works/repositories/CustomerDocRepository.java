package com.works.repositories;

import com.works.models.CustomerDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerDocRepository extends ElasticsearchRepository<CustomerDoc,String> {
    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"cname\":\"?0\"}},{\"match\":{\"csurname\":\"?0\"}},{\"match\":{\"c_email\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<CustomerDoc> findByName(String data, Pageable pageable);
}
