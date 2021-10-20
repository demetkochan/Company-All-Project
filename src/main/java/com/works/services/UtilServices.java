package com.works.services;

import com.works.entities.CategoryAnnouncement;
import com.works.entities.Customer;
import com.works.entities.Product;
import com.works.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilServices {


    final CategoryAnnouncementRepository caRepo;
    final ContentRepository cRepo;
    final NewsRepository nRepo;
    final AnnouncementRepository aRepo;
    final ProductRepository pRepo;
    final CustomerRepository customerRepo;
    final SurveyRepository sRepo;
    final OrderBoxRepository oRepo;

    public UtilServices(CategoryAnnouncementRepository caRepo, ContentRepository cRepo, NewsRepository nRepo, AnnouncementRepository aRepo, ProductRepository pRepo, CustomerRepository customerRepo, SurveyRepository sRepo, OrderBoxRepository oRepo) {
        this.caRepo = caRepo;
        this.cRepo = cRepo;
        this.nRepo = nRepo;
        this.aRepo = aRepo;
        this.pRepo = pRepo;
        this.customerRepo = customerRepo;
        this.sRepo = sRepo;
        this.oRepo = oRepo;
    }
    //Duyuru/haber kategori listeleme
    public List<CategoryAnnouncement>  categoryAnnouncementsList(){
        List<CategoryAnnouncement> ls1 = caRepo.findAll();
        return ls1;
    }

    //Toplam İçerik sayısı
    public int countContent(){
        int countContent = cRepo.countContent();
        return countContent;
    }
    //Toplam Haber Sayısı
    public int countNews(){
        int countNews = nRepo.countNews();
        return countNews;
    }
    //Toplam Aktif Haber Sayısı
    public int countNewsActive(){
        int countNewsActive = nRepo.countNewsActive();
        return countNewsActive;
    }

    //Toplam Pasif Haber Sayısı
    public int countNewsPassive(){
        int countNewsPassive = nRepo.countNewsPassive();
        return countNewsPassive;
    }

    //Toplam Duyuru Sayısı
    public int countAnnouncement(){
        int countAnnouncement = aRepo.countAnnouncement();
        return countAnnouncement;
    }
    //Toplam Ürün Sayısı
    public int countProduct(){
        int countProduct = pRepo.countProduct();
        return countProduct;
    }

    //Toplam müşteri Sayısı
    public int countCustomer(){
        int countCustomer = customerRepo.countCustomer();
        return countCustomer;
    }

    //Müşteri Listesi
    public List<Customer>  customerList(){
        List<Customer> ls = customerRepo.findAll();
        return ls;
    }

    //Ürün Listesi
    public List<Product>  productList(){
        List<Product> ls = pRepo.findAll();
        return ls;
    }

    //Toplam Anket Sayısı
    public int countSurvey(){
        int countSurvey = sRepo.countSurvey();
        return countSurvey;
    }

    //Toplam Sipariş Sayısı
    public int countOrder(){
        int countOrder= oRepo.countOrder();
        return countOrder;
    }

    //Toplam Beğeni Sayısı
    public int countLikeProduct(){
        int countLikeProduct= pRepo.countLikeProduct();
        return countLikeProduct;
    }




}
