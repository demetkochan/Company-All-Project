package com.works.elasticsearchdto;

import com.works.models.AnnouncementDoc;
import com.works.repositories.AnnouncementDocRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnnouncementDocDto {
    final AnnouncementDocRepository aRepo;
    final Util util;

    public AnnouncementDocDto(AnnouncementDocRepository aRepo, Util util) {
        this.aRepo = aRepo;
        this.util = util;
    }

    public Map<ERest, Object> add(AnnouncementDoc adoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        AnnouncementDoc announcementDoc = aRepo.save(adoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, announcementDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<AnnouncementDoc> iterableLs = aRepo.findAll();
        List<AnnouncementDoc> announcementList = new ArrayList<>();
        iterableLs.forEach(announcementList::add);
        hm.put(ERest.result,announcementList);
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
        Page<AnnouncementDoc> searchPage = aRepo.findByTitle(data, PageRequest.of(0, 10));
        List<AnnouncementDoc> announcementList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,announcementList);
        return hm;
    }

}
