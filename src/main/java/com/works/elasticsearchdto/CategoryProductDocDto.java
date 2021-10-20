package com.works.elasticsearchdto;

import com.works.models.CategoryAnnouncementDoc;
import com.works.models.CategoryProductDoc;
import com.works.repositories.CategoryProductDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryProductDocDto {
    final CategoryProductDocRepository cpRepo;

    public CategoryProductDocDto(CategoryProductDocRepository cpRepo) {
        this.cpRepo = cpRepo;
    }



    public Map<ERest, Object> add(CategoryProductDoc pdoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        CategoryProductDoc catproductDoc = cpRepo.save(pdoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, catproductDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<CategoryProductDoc> iterableLs = cpRepo.findAll();
        List<CategoryProductDoc> catproductList = new ArrayList<>();
        iterableLs.forEach(catproductList::add);
        hm.put(ERest.result,catproductList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(cpRepo.existsById(strIndex)){
                cpRepo.deleteById(strIndex);
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
        Page<CategoryProductDoc> searchPage = cpRepo.findByTitle(data, PageRequest.of(0, 10));
        List<CategoryProductDoc> catproductList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,catproductList);
        return hm;
    }
}
