package com.works.repositories;

import com.works.models.NewsDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsDocRepository extends ElasticsearchRepository<NewsDoc,String > {
}
