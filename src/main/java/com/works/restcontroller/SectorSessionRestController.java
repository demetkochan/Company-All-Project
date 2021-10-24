package com.works.restcontroller;

import com.works.models.SectorSession;
import com.works.redisrepository.SectorSessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/redis")
public class SectorSessionRestController {
    final SectorSessionRepository sRepo;

    public SectorSessionRestController(SectorSessionRepository sRepo) {
        this.sRepo = sRepo;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody SectorSession sectorSession){
        Map<String , Object> hm = new LinkedHashMap<>();
        SectorSession sectorSession1 =sRepo.save(sectorSession);
        hm.put("status", true);
        hm.put("result", sectorSession1);
        return hm;

    }

    @GetMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> hm = new LinkedHashMap<>();
        hm.put("status",true);
        Iterable<SectorSession> ls = sRepo.findAll();
        hm.put("result",ls);
        return hm;
    }
}
