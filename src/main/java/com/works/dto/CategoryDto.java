package com.works.dto;

import com.works.entities.*;
import com.works.util.Util;
import com.works.repositories.CategoryAnnouncementRepository;
import com.works.repositories.CategoryGalleryRepository;
import com.works.repositories.CategoryProductRepository;
import com.works.util.ERest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class CategoryDto {
    final CategoryAnnouncementRepository caRepo;
    final CategoryProductRepository cpRepo;
    final CategoryGalleryRepository cgRepo;
    final com.works.util.Util util;

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


    public Map<ERest, Object> categoryProductUpdate(CategoryProduct categoryProduct, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( categoryProduct.getId() != null ) {

                Optional<CategoryProduct> oCategoryProduct = cpRepo.findById(categoryProduct.getId());
                if ( oCategoryProduct.isPresent() ) {
                    try {
                        cpRepo.saveAndFlush(categoryProduct);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, categoryProduct);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, categoryProduct);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, categoryProduct);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, categoryProduct);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }


    public Map<ERest, Object> categoryGalleryUpdate(CategoryGallery categoryGallery, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( categoryGallery.getId() != null ) {

                Optional<CategoryGallery> optionalCategoryGallery = cgRepo.findById(categoryGallery.getId());
                if ( optionalCategoryGallery.isPresent() ) {
                    try {
                        cgRepo.saveAndFlush(categoryGallery);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, categoryGallery);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, categoryGallery);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, categoryGallery);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, categoryGallery);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }


    //haber güncelleme
    public Map<ERest, Object> categoryAnnouncementUpdate(CategoryAnnouncement categoryAnnouncement, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( categoryAnnouncement.getId() != null ) {

                Optional<CategoryAnnouncement> ocategoryAnnouncement = caRepo.findById(categoryAnnouncement.getId());
                if ( ocategoryAnnouncement.isPresent() ) {
                    try {
                        caRepo.saveAndFlush(categoryAnnouncement);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, categoryAnnouncement);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, categoryAnnouncement);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, categoryAnnouncement);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, categoryAnnouncement);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }




}
