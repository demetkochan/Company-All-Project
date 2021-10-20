package com.works.dto;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeDto {

    final ProductRepository pRepo;
    final Util util;


    public LikeDto(ProductRepository pRepo, Util util) {
        this.pRepo = pRepo;
        this.util = util;
    }

    //Beğenilen ürünler listesi
    public Map<ERest,Object> productLikelist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Product> ls = pRepo.likeResult();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Daha beğenilmeyen ürünler listesi
    public Map<ERest,Object> productDisLikelist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Product> ls = pRepo.dislikeResult();
        hm.put(ERest.result,ls);
        return hm;
    }



}
