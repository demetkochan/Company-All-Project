package com.works.elasticsearchdto;

import com.works.models.AnnouncementDoc;
import com.works.models.CategoryAnnouncementDoc;
import com.works.repositories.CategoryAnnouncementDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryAnnouncementDocDto {
    final CategoryAnnouncementDocRepository caRepo;

    public CategoryAnnouncementDocDto(CategoryAnnouncementDocRepository caRepo) {
        this.caRepo = caRepo;
    }

    public Map<ERest, Object> add(CategoryAnnouncementDoc adoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        CategoryAnnouncementDoc catannouncementDoc = caRepo.save(adoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, catannouncementDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<CategoryAnnouncementDoc> iterableLs = caRepo.findAll();
        List<CategoryAnnouncementDoc> catannouncementList = new ArrayList<>();
        iterableLs.forEach(catannouncementList::add);
        hm.put(ERest.result,catannouncementList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(caRepo.existsById(strIndex)){
                caRepo.deleteById(strIndex);
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
        Page<CategoryAnnouncementDoc> searchPage = caRepo.findByTitle(data, PageRequest.of(0, 10));
        List<CategoryAnnouncementDoc> announcementList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,announcementList);
        return hm;
    }
}
