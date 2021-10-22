package com.works.dto;

import com.works.repositories.*;
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
    final CustomerRepository cuRepo;
    final OrderBoxRepository oRepo;
    final SurveyRepository sRepo;
    final Util util;

    public HomeDto(ContentRepository cRepo, NewsRepository nRepo, AnnouncementRepository aRepo, ProductRepository pRepo, CustomerRepository cuRepo, OrderBoxRepository oRepo, SurveyRepository sRepo, Util util) {
        this.cRepo = cRepo;
        this.nRepo = nRepo;
        this.aRepo = aRepo;
        this.pRepo = pRepo;
        this.cuRepo = cuRepo;
        this.oRepo = oRepo;
        this.sRepo = sRepo;
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

    //Toplam Müşteri
    public Map<ERest,Object> totalCustomer(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumCustomer = cuRepo.countCustomer();
        hm.put(ERest.result,sumCustomer);
        return hm;
    }

    //Toplam Sipariş
    public Map<ERest,Object> totalOrder(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumOrder = oRepo.countOrder();
        hm.put(ERest.result,sumOrder);
        return hm;
    }

    //Toplam Anket
    public Map<ERest,Object> totalSurvey(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int sumSurvey = sRepo.countSurvey();
        hm.put(ERest.result,sumSurvey);
        return hm;
    }









}
