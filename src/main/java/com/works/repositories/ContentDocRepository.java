package com.works.repositories;

import com.works.models.AnnouncementDoc;
import com.works.models.ContentDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ContentDocRepository extends ElasticsearchRepository<ContentDoc,String> {
    @Query("{\"bool\":{\"must\":[{\"match\":{\"contenttitle\":\"?0\"}},{\"match\":{\"content_desc\":\"?0\"}},{\"match\":{\"content_detail_desc\":\"?0\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<ContentDoc> findByTitle(String data, Pageable pageable);
}
