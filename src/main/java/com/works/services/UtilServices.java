package com.works.services;

import com.works.entities.CategoryAnnouncement;
import com.works.repositories.CategoryAnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilServices {


    final CategoryAnnouncementRepository caRepo;

    public UtilServices(CategoryAnnouncementRepository caRepo) {
        this.caRepo = caRepo;
    }
    //Duyuru/haber kategori listeleme
    public List<CategoryAnnouncement>  categoryAnnouncementsList(){
        List<CategoryAnnouncement> ls1 = caRepo.findAll();
        return ls1;
    }
}
