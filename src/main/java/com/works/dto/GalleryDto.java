package com.works.dto;


import com.works.entities.*;
import com.works.repositories.GalleryImageRepository;
import com.works.repositories.GalleryRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class GalleryDto {

    final GalleryRepository gRepo;
    final GalleryImageRepository gıRepo;
    final Util util;
    final private String UPLOAD_DIR="src/main/resources/static/uploads/";

    public GalleryDto(GalleryRepository gRepo, GalleryImageRepository gıRepo, Util util) {
        this.gRepo = gRepo;
        this.gıRepo = gıRepo;
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

    //Galeri Güncelleme
    public Map<ERest, Object> galleryUpdate(Gallery gallery,BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( gallery.getId() != null ) {

                Optional<Gallery> oProduct= gRepo.findById(gallery.getId());
                if ( oProduct.isPresent() ) {
                    try {
                        gRepo.saveAndFlush(gallery);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, gallery);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, gallery);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, gallery);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, gallery);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }

    //Galeriye Resim ekleme
    public Map<String, Object> uploadImage(MultipartFile file, GalleryImageInterlayer galleryImage) {
        int sendSuccessCount = 0;
        String errorMessage = "";
        Map<String, Object> hm = new LinkedHashMap<>();
        System.out.println(galleryImage);
        if (!file.isEmpty() ) {
            File theDir = new File(UPLOAD_DIR + "products/" + galleryImage.getG_id());
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String ext = fileName.substring(fileName.length()-5, fileName.length());
            System.out.println(ext);
            String uui = UUID.randomUUID().toString();
            fileName = uui + ext;
            try {
                Path path = Paths.get(UPLOAD_DIR + "products/" + galleryImage.getG_id()+ "/" + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            GalleryImage galleryImage1 = new GalleryImage();

            galleryImage1.setGallery_image(fileName);
            galleryImage1.setImage_desc(galleryImage.getImage_desc());
            galleryImage1.setG_id(galleryImage.getG_id());


            gıRepo.save(galleryImage1);

        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put("status", true);
            hm.put("message", "Yükleme Başarılı");
        }else {
            hm.put("status", false);
            hm.put("message", errorMessage);
        }

        return hm;
    }

    //galeri resimleri listeleme
    public Map<ERest,Object> galleryImageList(String id){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int pid = Integer.parseInt(id);
        List<GalleriesImages> ls = gıRepo.galleriesImagesList(pid);
        hm.put(ERest.result,ls);
        return hm;
    }

    //Galeri resim silme
    public Map<ERest, Object>galleryImageDelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int pid = Integer.parseInt(strIndex);
        try{
            if(gıRepo.existsById(pid)){
                gıRepo.deleteById(pid);
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
