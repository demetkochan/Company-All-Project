package com.works.dto;

import com.works.Util.Util;
import com.works.entities.Content;
import com.works.repositories.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentDto {

    final ContentRepository cRepo;
    final Util util;

    public ContentDto(ContentRepository cRepo, Util util) {
        this.cRepo = cRepo;
        this.util = util;
    }

    //içerik ekleme
    public Map<String, Object> Contentadd(Content content, BindingResult bResult) {
        Map<String , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Content contentDb = cRepo.save(content);
                hm.put("status", true);
                hm.put("message", "Ekleme başarılı");
                hm.put("result", contentDb);
            } catch (Exception e) {
                hm.put("status", false);
            }

        }else{
            hm.put("status",false);
            hm.put("errors", util.errors(bResult));
        }

        return hm;
    }

    //İçerik listeleme
    public Map<String,Object> contentlist(){
        Map<String,Object> hm = new LinkedHashMap<>();
        hm.put("status",true);
        List<Content> ls = cRepo.findAll();
        hm.put("result",ls);
        return hm;
    }

    //içerik Silme
    public Map<String, Object>Contentdelete (String strIndex){
        Map<String, Object> hm = new HashMap<>();
        int cuid = Integer.parseInt(strIndex);
        try{
            if(cRepo.existsById(cuid)){
                cRepo.deleteById(cuid);
                hm.put("status", true);
                hm.put("message", "silme başarılı");
                hm.put("result", cuid);
            }else{
                hm.put("status", false);
                hm.put("message", "Silme Başarısız. Girilen Id yanlış");
                hm.put("result", cuid);
            }
        }catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "silme gerçekleşmedi");
            hm.put("result", cuid);
        }
        return hm;
    }

    //Duruma göre içerik Listeleme
    public Map<String,Object> ContentProcess(String process_id){
        Map<String,Object> hm = new LinkedHashMap<>();
        int cuid = Integer.parseInt(process_id);
        hm.put("status",true);
        List<Content> ls = cRepo.process(cuid);
        hm.put("result",ls);
        return hm;
    }

}
