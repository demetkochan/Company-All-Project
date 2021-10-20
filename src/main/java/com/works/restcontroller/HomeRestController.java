package com.works.restcontroller;


import com.works.dto.HomeDto;
import com.works.util.ERest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/home_rest")
public class HomeRestController {

    final HomeDto homeDto;

    public HomeRestController(HomeDto homeDto) {
        this.homeDto = homeDto;
    }

    //Toplam İçerik
    @GetMapping("/contentCount")
    public Map<ERest,Object> contentCount(){
        return homeDto.totalContent();
    }

    //Toplam Haber
    @GetMapping("/newsCount")
    public Map<ERest,Object> newsCount(){
        return homeDto.totalNews();
    }
    //Toplam Haber Aktif
    @GetMapping("/newsActiveCount")
    public Map<ERest,Object> newsActiveCount(){
        return homeDto.totalNewsActive();
    }
    //Toplam Haber Pasif
    @GetMapping("/newsPassiveCount")
    public Map<ERest,Object> newsPassiveCount(){
        return homeDto.totalNewsPassive();
    }

    //Toplam Duyuru
    @GetMapping("/announcementCount")
    public Map<ERest,Object> announcementCount(){
        return homeDto.totalAnnouncement();
    }

    //Toplam Ürün
    @GetMapping("/productCount")
    public Map<ERest,Object> productCount(){
        return homeDto.totalProduct();
    }

    //Toplam Beğeni
    @GetMapping("/productlikeCount")
    public Map<ERest,Object> productlikeCount(){
        return homeDto.totalProductLike();
    }


}
