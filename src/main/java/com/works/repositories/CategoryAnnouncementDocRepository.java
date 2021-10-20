package com.works.repositories;


import com.works.models.CategoryAnnouncementDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryAnnouncementDocRepository extends ElasticsearchRepository<CategoryAnnouncementDoc,String> {

    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"newscategoryname\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}")
    Page<CategoryAnnouncementDoc> findByTitle(String data, Pageable pageable);
}
