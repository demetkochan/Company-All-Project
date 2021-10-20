package com.works.elasticsearchdto;

import com.works.models.CategoryAnnouncementDoc;
import com.works.models.CategoryGalleryDoc;
import com.works.repositories.CategoryGalleryDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryGalleryDocDto {
    final CategoryGalleryDocRepository cgRepo;

    public CategoryGalleryDocDto(CategoryGalleryDocRepository cgRepo) {
        this.cgRepo = cgRepo;
    }

    public Map<ERest, Object> add(CategoryGalleryDoc cgdoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        CategoryGalleryDoc catgalleryDoc = cgRepo.save(cgdoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, catgalleryDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<CategoryGalleryDoc> iterableLs = cgRepo.findAll();
        List<CategoryGalleryDoc> catgalleryList = new ArrayList<>();
        iterableLs.forEach(catgalleryList::add);
        hm.put(ERest.result,catgalleryList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(cgRepo.existsById(strIndex)){
                cgRepo.deleteById(strIndex);
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
        Page<CategoryGalleryDoc> searchPage = cgRepo.findByTitle(data, PageRequest.of(0, 10));
        List<CategoryGalleryDoc> catgalleryList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,catgalleryList);
        return hm;
    }
}
