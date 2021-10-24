package com.works.redisrepository;

import com.works.models.SectorSession;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
public interface SectorSessionRepository extends CrudRepository<SectorSession,Integer> {
}
