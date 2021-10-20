package com.works.dto;

import com.works.repositories.AnnouncementRepository;
import com.works.repositories.ContentRepository;
import com.works.repositories.NewsRepository;
import com.works.repositories.ProductRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HomeDto {

    final ContentRepository cRepo;
    final NewsRepository nRepo;
    final AnnouncementRepository aRepo;
    final ProductRepository pRepo;
    final Util util;

    public HomeDto(ContentRepository cRepo, NewsRepository nRepo, AnnouncementRepository aRepo, ProductRepository pRepo, Util util) {
        this.cRepo = cRepo;
        this.nRepo = nRepo;
        this.aRepo = aRepo;
        this.pRepo = pRepo;
        this.util = util;
    }

    //Toplam İçerik
    public Map<ERest,Object> totalContent(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumContent = cRepo.countContent();
        hm.put(ERest.result,sumContent);
        return hm;
    }

    //Toplam Haber
    public Map<ERest,Object> totalNews(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumNews = nRepo.countNews();
        hm.put(ERest.result,sumNews);
        return hm;
    }

    //Toplam Aktif Haber
    public Map<ERest,Object> totalNewsActive(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumNewsActive = nRepo.countNewsActive();
        hm.put(ERest.result,sumNewsActive);
        return hm;
    }

    //Toplam Pasif Haber
    public Map<ERest,Object> totalNewsPassive(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumNewsPassive = nRepo.countNewsPassive();
        hm.put(ERest.result,sumNewsPassive);
        return hm;
    }

    //Toplam Duyuru
    public Map<ERest,Object> totalAnnouncement(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumAnnouncement = aRepo.countAnnouncement();
        hm.put(ERest.result,sumAnnouncement);
        return hm;
    }
    //Toplam Ürün
    public Map<ERest,Object> totalProduct(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumProduct = pRepo.countProduct();
        hm.put(ERest.result,sumProduct);
        return hm;
    }

    //Toplam Beğeni
    public Map<ERest,Object> totalProductLike(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumProduct = pRepo.countLikeProduct();
        hm.put(ERest.result,sumProduct);
        return hm;
    }






}
