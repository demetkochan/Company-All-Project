package com.works.dto;

import com.works.Util.Util;
import com.works.entities.CategoryAnnouncement;
import com.works.entities.CategoryGallery;
import com.works.entities.CategoryProduct;
import com.works.entities.Content;
import com.works.repositories.CategoryAnnouncementRepository;
import com.works.repositories.CategoryGalleryRepository;
import com.works.repositories.CategoryProductRepository;
import com.works.util.ERest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryDto {
    final CategoryAnnouncementRepository caRepo;
    final CategoryProductRepository cpRepo;
    final CategoryGalleryRepository cgRepo;
    final com.works.Util.Util util;

    public CategoryDto(CategoryAnnouncementRepository caRepo, CategoryProductRepository cpRepo, CategoryGalleryRepository cgRepo, Util util) {
        this.caRepo = caRepo;
        this.cpRepo = cpRepo;
        this.cgRepo = cgRepo;
        this.util = util;

    }
    public Map<ERest, Object> CategoryAnnouncementAdd(CategoryAnnouncement announcement, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                CategoryAnnouncement announcementDb = caRepo.save(announcement);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, announcementDb);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }
        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }
    public Map<ERest, Object> CategoryProductAdd(CategoryProduct categoryProduct, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                CategoryProduct productDb = cpRepo.save(categoryProduct);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, productDb);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }
        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }
    public Map<ERest, Object> CategoryGalleryAdd(CategoryGallery gallery, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                CategoryGallery galleryDb = cgRepo.save(gallery);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, galleryDb);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }
        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }

    public Map<ERest,Object> announcementlist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<CategoryAnnouncement> ls = caRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }
    public Map<ERest,Object> productlist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<CategoryProduct> ls = cpRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }
    public Map<ERest,Object> gallerylist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<CategoryGallery> ls = cgRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    public Map<ERest, Object>announcementdelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int aid = Integer.parseInt(strIndex);
        try{
            if(caRepo.existsById(aid)){
                caRepo.deleteById(aid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, aid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, aid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, aid);
        }
        return hm;
    }
    public Map<ERest, Object>Productdelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int pid = Integer.parseInt(strIndex);
        try{
            if(cpRepo.existsById(pid)){
                cpRepo.deleteById(pid);
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
    public Map<ERest, Object>Gallerydelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int gid = Integer.parseInt(strIndex);
        try{
            if(cgRepo.existsById(gid)){
                cgRepo.deleteById(gid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, gid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, gid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, gid);
        }
        return hm;
    }



}
