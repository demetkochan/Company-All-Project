package com.works.elasticsearchdto;


import com.works.models.ContentDoc;
import com.works.repositories.ContentDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentDocDto {
    final ContentDocRepository cRepo;

    public ContentDocDto(ContentDocRepository cRepo) {
        this.cRepo = cRepo;
    }
    public Map<ERest, Object> add(ContentDoc cdoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        ContentDoc contentDoc = cRepo.save(cdoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, contentDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<ContentDoc> iterableLs = cRepo.findAll();
        List<ContentDoc> contentList = new ArrayList<>();
        iterableLs.forEach(contentList::add);
        hm.put(ERest.result,contentList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(cRepo.existsById(strIndex)){
                cRepo.deleteById(strIndex);
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
        Page<ContentDoc> searchPage = cRepo.findByTitle(data, PageRequest.of(0, 10));
        List<ContentDoc> contentList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,contentList);
        return hm;
    }
}
