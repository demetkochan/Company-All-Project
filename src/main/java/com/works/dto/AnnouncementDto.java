package com.works.dto;

import com.works.entities.News;
import com.works.entities.NewsInterLayer;
import com.works.util.ERest;
import com.works.util.Util;
import com.works.entities.Announcement;
import com.works.entities.Content;
import com.works.repositories.AnnouncementRepository;
import com.works.repositories.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class AnnouncementDto {

    final AnnouncementRepository aRepo;
    final NewsRepository nRepo;
    final Util util;

    public AnnouncementDto(AnnouncementRepository aRepo, NewsRepository nRepo, Util util) {
        this.aRepo = aRepo;
        this.nRepo = nRepo;
        this.util = util;
    }

    private final String UPLOAD_DIR =  "src/main/resources/static/uploads/";
    long maxFileUploadSize = 2048;

    //Duyuru Ekleme ekleme
    public Map<ERest, Object> Announcementadd(Announcement announcement, BindingResult bResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Announcement announcementDb = aRepo.save(announcement);
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
    //Duyuru listeleme
    public Map<ERest,Object> announcementlist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Announcement> ls = aRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Duyuru silme
    public Map<ERest, Object> Announcementdelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int cuid = Integer.parseInt(strIndex);
        try{
            if(aRepo.existsById(cuid)){
                aRepo.deleteById(cuid);
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

    //Haber Ekleme
    public Map<ERest, Object> upload(MultipartFile file, NewsInterLayer news) {
        int sendSuccessCount = 0;
        String errorMessage = "";
        Map<ERest, Object> hm = new LinkedHashMap<>();
        System.out.println(news);
        if (!file.isEmpty() ) {
            long fileSizeMB = file.getSize() / 1024;
            if ( fileSizeMB > maxFileUploadSize ) {
                System.err.println("Dosya boyutu çok büyük Max 2MB");
                errorMessage = "Dosya boyutu çok büyük Max "+ (maxFileUploadSize / 1024) +"MB olmalıdır";
            }else {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String ext = fileName.substring(fileName.length()-5, fileName.length());
                String uui = UUID.randomUUID().toString();
                fileName = uui + ext;
                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    sendSuccessCount += 1;

                    // add database
                    News news1 = new News();
                    news1.setNewstitle(news.getNewstitle());
                    news1.setNews_image(fileName);
                    news1.setNews_desc(news.getNews_desc());
                    news1.setNews_detail_desc(news1.getNews_detail_desc());
                    news1.setNews_status(news.getNews_status());
                    news1.setNews_category(news.getNews_category());
                    nRepo.save(news1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Yükleme Başarılı");
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, errorMessage);
        }

        return hm;
    }

    //Haber Listeleme
    public Map<ERest,Object> newsList(){
        Map<ERest ,Object> hm=new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<News> ls = nRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Haber Silme
    public Map<ERest, Object> newsdelete (String strlid){
        Map<ERest, Object> hm = new HashMap<>();
        int nid = Integer.parseInt(strlid);
        try{
            if(nRepo.existsById(nid)){
                nRepo.deleteById(nid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Silme işlemi başarılı!");
                hm.put(ERest.result, nid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, nid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme işlemi gerçekleşmedi");
            hm.put(ERest.result, nid);
        }
        return hm;
    }

    //duyuru güncelleme
    public Map<ERest, Object> announcementUpdate(Announcement announcement, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( announcement.getId() != null ) {

                Optional<Announcement> oAnnouncement = aRepo.findById(announcement.getId());
                if ( oAnnouncement.isPresent() ) {
                    try {
                        aRepo.saveAndFlush(announcement);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, announcement);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, announcement);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, announcement);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, announcement);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }

    //haber güncelleme
    public Map<ERest, Object> newsUpdate(News news, BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( news.getId() != null ) {

                Optional<News> oNews = nRepo.findById(news.getId());
                if ( oNews.isPresent() ) {
                    try {
                        nRepo.saveAndFlush(news);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, news);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, news);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, news);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, news);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }

    //Haber status e göre listeleme
    public Map<ERest,Object> NewsProcess(String process_id){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        int cuid = Integer.parseInt(process_id);
        hm.put(ERest.status,true);
        List<News> ls = nRepo.statusproces(cuid);
        hm.put(ERest.result,ls);
        return hm;
    }

    //Haber Kategoriye göre listeleme
    public Map<ERest,Object> NewsCategory(String category_id){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        int cuid = Integer.parseInt(category_id);
        hm.put(ERest.status,true);
        List<News> ls = nRepo.process(cuid);
        hm.put(ERest.result,ls);
        return hm;
    }

    //Duyuru Statuse göre listeleme
    public Map<ERest,Object> AnnouncementStatus(String process_id){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        int cuid = Integer.parseInt(process_id);
        hm.put(ERest.status,true);
        List<Announcement> ls = aRepo.process(cuid);
        hm.put(ERest.result,ls);
        return hm;
    }





}





