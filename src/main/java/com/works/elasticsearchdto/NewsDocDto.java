package com.works.elasticsearchdto;

import com.works.models.AdvertisingDoc;
import com.works.models.NewsDoc;
import com.works.repositories.NewsDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsDocDto {
    final NewsDocRepository nRepo;

    public NewsDocDto(NewsDocRepository nRepo) {
        this.nRepo = nRepo;
    }
    public Map<ERest, Object> add(NewsDoc ndoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        NewsDoc newsList = nRepo.save(ndoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, newsList);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<NewsDoc> iterableLs = nRepo.findAll();
        List<NewsDoc> newsList = new ArrayList<>();
        iterableLs.forEach(newsList::add);
        hm.put(ERest.result,newsList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(nRepo.existsById(strIndex)){
                nRepo.deleteById(strIndex);
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
        Page<NewsDoc> searchPage = nRepo.findByTitle(data, PageRequest.of(0, 10));
        List<NewsDoc> newsList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,newsList);
        return hm;
    }
}
