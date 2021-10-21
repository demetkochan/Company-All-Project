package com.works.elasticsearchdto;

import com.works.models.AdvertisingDoc;
import com.works.models.ProductDoc;
import com.works.repositories.ProductDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductDocDto {
    final ProductDocRepository pRepo;

    public ProductDocDto(ProductDocRepository pRepo) {
        this.pRepo = pRepo;
    }
    public Map<ERest, Object> add(ProductDoc pdoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        ProductDoc productDoc = pRepo.save(pdoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, productDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<ProductDoc> iterableLs = pRepo.findAll();
        List<ProductDoc> productDocsList = new ArrayList<>();
        iterableLs.forEach(productDocsList::add);
        hm.put(ERest.result,productDocsList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(pRepo.existsById(strIndex)){
                pRepo.deleteById(strIndex);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Silme başarılı");
                hm.put(ERest.result, strIndex);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, strIndex);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme gerçekleşmedi");
            hm.put(ERest.result, strIndex);
        }
        return hm;
    }


    public Map<ERest, Object> search(String data) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        Page<ProductDoc> searchPage = pRepo.findByTitle(data, PageRequest.of(0, 10));
        List<ProductDoc> productList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,productList);
        return hm;
    }
}
