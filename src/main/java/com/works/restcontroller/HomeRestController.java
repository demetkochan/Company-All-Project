package com.works.restcontroller;


import com.works.dto.HomeDto;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/home_rest")
@Api(value ="HomeRestController",authorizations ={@Authorization(value = "basicAuth")})

public class HomeRestController {

    final HomeDto homeDto;

    public HomeRestController(HomeDto homeDto) {
        this.homeDto = homeDto;
    }

    //Toplam İçerik
    @ApiOperation("Toplam İçerik sayısı")
    @GetMapping("/contentCount")
    public Map<ERest,Object> contentCount(){
        return homeDto.totalContent();
    }

    //Toplam Haber
    @ApiOperation("Toplam Haber sayısı")
    @GetMapping("/newsCount")
    public Map<ERest,Object> newsCount(){
        return homeDto.totalNews();
    }

    //Toplam Haber Aktif
    @ApiOperation("Toplam Aktif Haber Sayısı")
    @GetMapping("/newsActiveCount")
    public Map<ERest,Object> newsActiveCount(){
        return homeDto.totalNewsActive();
    }

    //Toplam Haber Pasif
    @ApiOperation("Toplam Pasif Haber Sayısı")
    @GetMapping("/newsPassiveCount")
    public Map<ERest,Object> newsPassiveCount(){
        return homeDto.totalNewsPassive();
    }

    //Toplam Duyuru
    @ApiOperation("Toplam Duyuru Sayısı")
    @GetMapping("/announcementCount")
    public Map<ERest,Object> announcementCount(){
        return homeDto.totalAnnouncement();
    }

    //Toplam Ürün
    @ApiOperation("Toplam Ürün sayısı")
    @GetMapping("/productCount")
    public Map<ERest,Object> productCount(){
        return homeDto.totalProduct();
    }

    //Toplam Beğeni
    @ApiOperation("Toplam Beğeni sayısı")
    @GetMapping("/productlikeCount")
    public Map<ERest,Object> productlikeCount(){
        return homeDto.totalProductLike();
    }

    //Toplam Müşteri
    @ApiOperation("Toplam Müşteri sayısı")
    @GetMapping("/customerCount")
    public Map<ERest,Object> customerCount(){
        return homeDto.totalCustomer();
    }

    //Toplam Sipariş
    @ApiOperation("Toplam Sipariş sayısı")
    @GetMapping("/orderCount")
    public Map<ERest,Object> orderCount(){
        return homeDto.totalOrder();
    }

    //Toplam Anket
    @ApiOperation("Toplam Anket Sayısı")
    @GetMapping("/surveyCount")
    public Map<ERest,Object> surveyCount(){
        return homeDto.totalSurvey();
    }




}
