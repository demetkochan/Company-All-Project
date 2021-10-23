package com.works.dto;


import com.works.entities.Gallery;
import com.works.entities.Product;
import com.works.repositories.GalleryRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GalleryDto {

    final GalleryRepository gRepo;
    final Util util;

    public GalleryDto(GalleryRepository gRepo, Util util) {
        this.gRepo = gRepo;
        this.util = util;
    }

    //Galeri Ekleme
    public Map<ERest, Object> Galeryadd(Gallery gallery, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Gallery Galeryadd = gRepo.save(gallery);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, Galeryadd);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }

        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }

    //Galeri Listeleme
    public Map<ERest,Object> galeryList(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Gallery> ls = gRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Galeri Silme
    public Map<ERest, Object>gallerydelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int pid = Integer.parseInt(strIndex);
        try{
            if(gRepo.existsById(pid)){
                gRepo.deleteById(pid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, pid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, pid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, pid);
        }
        return hm;
    }
}
