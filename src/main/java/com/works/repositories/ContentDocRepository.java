package com.works.repositories;

import com.works.models.ContentDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ContentDocRepository extends ElasticsearchRepository<ContentDoc,String> {
    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"contenttitle\":\"?0\"}},{\"match\":{\"content_desc\":\"?0\"}},{\"match\":{\"content_detail_desc\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<ContentDoc> findByTitle(String data, Pageable pageable);

    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"contenttitle\":\"?0\"}},{\"match\":{\"content_desc\":\"?0\"}},{\"match\":{\"content_detail_desc\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    List<ContentDoc> find(String data);
}
