package com.works.dto;

import com.works.entities.Product;
import com.works.util.Util;
import com.works.entities.Content;
import com.works.repositories.ContentRepository;
import com.works.util.ERest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ContentDto {

    final ContentRepository cRepo;
    final Util util;

    public ContentDto(ContentRepository cRepo, Util util) {
        this.cRepo = cRepo;
        this.util = util;
    }

    //içerik ekleme
    public Map<ERest, Object> Contentadd(Content content, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Content contentDb = cRepo.save(content);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, contentDb);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }

        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }

    //İçerik listeleme
    public Map<ERest,Object> contentlist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Content> ls = cRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //içerik Silme
    public Map<ERest, Object>Contentdelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int cuid = Integer.parseInt(strIndex);
        try{
            if(cRepo.existsById(cuid)){
                cRepo.deleteById(cuid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, cuid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, cuid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, cuid);
        }
        return hm;
    }

    //Duruma göre içerik Listeleme
    public Map<ERest,Object> ContentProcess(String process_id){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        int cuid = Integer.parseInt(process_id);
        hm.put(ERest.status,true);
        List<Content> ls = cRepo.process(cuid);
        hm.put(ERest.result,ls);
        return hm;
    }

    //içerik güncelleme
    public Map<ERest, Object> contentUpdate(Content content, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( content.getId() != null ) {

                Optional<Content> oContent = cRepo.findById(content.getId());
                if ( oContent.isPresent() ) {
                    try {
                        cRepo.saveAndFlush(content);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, content);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, content);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, content);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, content);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }

}
