package com.works.dto;

import com.works.Util.Util;
import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ProductDto {
    final ProductRepository pRepo;
    final Util util;

    public ProductDto(ProductRepository pRepo, Util util) {
        this.pRepo = pRepo;
        this.util = util;
    }

    //ürün ekleme
    public Map<com.works.util.ERest, Object> Productadd(Product product, BindingResult bResult) {
        Map<com.works.util.ERest, Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Product product1 = pRepo.save(product);
                hm.put(com.works.util.ERest.status, true);
                hm.put(com.works.util.ERest.message, "Ekleme başarılı");
                hm.put(com.works.util.ERest.result, product1);
            } catch (Exception e) {
                hm.put(com.works.util.ERest.status, false);
            }

        }else{
            hm.put(com.works.util.ERest.status,false);
            hm.put(com.works.util.ERest.errors, util.errors(bResult));
        }

        return hm;
    }

}
