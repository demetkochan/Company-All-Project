package com.works.repositories;

import com.works.models.ContentDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ContentDocRepository extends ElasticsearchRepository<ContentDoc,String> {
}
