package com.works.repositories;


import com.works.models.NewsDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsDocRepository extends ElasticsearchRepository<NewsDoc,String > {
    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"newstitle\":\"?0\"}},{\"match\":{\"news_desc\":\"?0\"}},{\"match\":{\"news_detail_desc\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<NewsDoc> findByTitle(String data, Pageable pageable);
}
