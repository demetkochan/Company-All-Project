package com.works.elasticsearchdto;


import com.works.models.AdvertisingDoc;
import com.works.repositories.AdvertisingDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdvertisingDocDto {
    final AdvertisingDocRepository aRepo;

    public AdvertisingDocDto(AdvertisingDocRepository aRepo) {
        this.aRepo = aRepo;
    }

    public Map<ERest, Object> add(AdvertisingDoc adoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        AdvertisingDoc advertisingDoc = aRepo.save(adoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, advertisingDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<AdvertisingDoc> iterableLs = aRepo.findAll();
        List<AdvertisingDoc> advertisingList = new ArrayList<>();
        iterableLs.forEach(advertisingList::add);
        hm.put(ERest.result,advertisingList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(aRepo.existsById(strIndex)){
                aRepo.deleteById(strIndex);
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
        Page<AdvertisingDoc> searchPage = aRepo.findByTitle(data, PageRequest.of(0, 10));
        List<AdvertisingDoc> advertisingList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,advertisingList);
        return hm;
    }
}
