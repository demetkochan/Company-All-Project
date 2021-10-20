package com.works.repositories;

import com.works.models.AnnouncementDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AnnouncementDocRepository extends ElasticsearchRepository<AnnouncementDoc,String> {
@Query("{\"query\":{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"announcementtitle\":\"0?\"}},{\"match\":{\"announcement_detail_desc\":\"0?\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
Page<AnnouncementDoc> findByTitle(String data, Pageable pageable);
}
