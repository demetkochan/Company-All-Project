package com.works.controller;

import com.works.entities.Gallery;
import com.works.repositories.CategoryGalleryRepository;
import com.works.repositories.GalleryRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gallery_mvc")
public class GalleryController {

    private static final Logger log=Logger.getLogger(ProductController.class);

    final CategoryGalleryRepository cgRepo;
    final GalleryRepository gRepo;

    public GalleryController(CategoryGalleryRepository cgRepo, GalleryRepository gRepo) {
        this.cgRepo = cgRepo;
        this.gRepo = gRepo;
    }

    @GetMapping("")
    public String gallery(Model model) {
        model.addAttribute("galeries",cgRepo.findAll());
        return "gallery";
    }


    Gallery galleryUpdate = new Gallery();

    @ResponseBody
    @PostMapping("/add")
    public Gallery galleryAdd(@RequestBody Gallery gallery){

        try{
            if(galleryUpdate.getId() != null && galleryUpdate.getId() > 0){
                gallery.setId(galleryUpdate.getId());
            }
            gRepo.saveAndFlush(gallery);
            galleryUpdate = new Gallery();

        }catch (Exception ex){
            log.error("Ürün ekleme veya günceleme hatasıdır.");
            System.err.println("İşlem sırasında hata oluştur!");
        }

        return galleryUpdate;

    }

    //gallery Listeleme
    @ResponseBody
    @GetMapping("/list")
    public List<Gallery> list(){
        return gRepo.findAll();
    }

    //gallery silme
    @ResponseBody
    @DeleteMapping(value = "/delete/{stid}")
    public String delete(@PathVariable String stid) {
        String status = "0";
        try{
            int pid = Integer.parseInt(stid);
            gRepo.deleteById(pid);
            status= "1";

        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }
}
