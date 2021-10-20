package com.works.repositories;

import com.works.models.AdvertisingDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AdvertisingDocRepository extends ElasticsearchRepository<AdvertisingDoc,String> {
}
