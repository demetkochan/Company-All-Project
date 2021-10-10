package com.works.controller;

import com.works.entities.CategoryAnnouncement;
import com.works.entities.CategoryGallery;
import com.works.entities.CategoryProduct;
import com.works.entities.Content;
import com.works.repositories.CategoryAnnouncementRepository;
import com.works.repositories.CategoryGalleryRepository;
import com.works.repositories.CategoryProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category_mvc")
public class CategoryController {

    final CategoryAnnouncementRepository caRepo;
    final CategoryProductRepository cpRepo;
    final CategoryGalleryRepository cgRepo;

    public CategoryController(CategoryAnnouncementRepository caRepo, CategoryProductRepository cpRepo, CategoryGalleryRepository cgRepo) {
        this.caRepo = caRepo;
        this.cpRepo = cpRepo;
        this.cgRepo = cgRepo;
    }

    @GetMapping("")
    public String category(Model model){
        return "category";
    }
    CategoryAnnouncement announcementUpdate=new CategoryAnnouncement();
    @ResponseBody
    @PostMapping("/announcementAdd")
    public CategoryAnnouncement add(@RequestBody CategoryAnnouncement announcement){
        try{
            if(announcementUpdate.getId() != null && announcementUpdate.getId() > 0){
                announcement.setId(announcementUpdate.getId());
            }
            caRepo.saveAndFlush(announcement);
            announcementUpdate = new CategoryAnnouncement();

        }catch (Exception ex){
            System.err.println("İşlem sırasında hata oluştur!");
        }

        return announcementUpdate;

    }
    @ResponseBody
    @GetMapping("/newsList/{pageNumber}/{stSize}")
    public List<CategoryAnnouncement> list(@RequestParam(defaultValue = "0") String pageNumber, @RequestParam(defaultValue = "10") String stSize ) {
        if(pageNumber==null && stSize==null){
                Pageable pageable = PageRequest.of(0, 10);
            List<CategoryAnnouncement> pageList = caRepo.findByOrderByIdAsc(pageable);
            long totalcount = caRepo.count();
            return pageList;
            }else {
            int ipageNumber = Integer.parseInt(pageNumber);
            int size = Integer.parseInt(stSize);
                Pageable pageable = PageRequest.of(ipageNumber, size);
                List<CategoryAnnouncement> pageList = caRepo.findByOrderByIdAsc(pageable);
                long totalcount = caRepo.count();
            return pageList;
            }

    }

  /*  @ResponseBody
    @GetMapping("/announcementList")
    public List<CategoryAnnouncement> list(){
        return caRepo.findAll();
    }*/

    @ResponseBody
    @DeleteMapping(value = "/newsDelete/{stId}")
    public String delete(@PathVariable String stId) {
        String status = "0";
        try{
            int id = Integer.parseInt(stId);
            caRepo.deleteById(id);
            status= "1";

        }catch (Exception e){
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }
    @GetMapping("/newsCategoryUpdate/{stId}")
    public String newsCategoryUpdate(@PathVariable String stId){
        try {
            int id = Integer.parseInt(stId);
           CategoryAnnouncement newsUpdate = caRepo.findById(id).get();
        }catch (Exception ex) {
            System.err.println( "Update işlemi sırasında bir hata oluştu!");
        }
        return "redirect:/category_mvc";
    }

    @ResponseBody
    @PostMapping("/galleryAdd")
    public CategoryGallery add(@RequestBody CategoryGallery categoryGallery){
        CategoryGallery cg= cgRepo.save(categoryGallery);
        return cg;
    }

    @ResponseBody
    @GetMapping("/galleryList")
    public List<CategoryGallery> List(){
        return cgRepo.findAll();
    }

    @ResponseBody
    @DeleteMapping(value = "/galleryDelete/{stId}")
    public String Delete(@PathVariable String stId) {
        String status = "0";
        try{
            int id = Integer.parseInt(stId);
            cgRepo.deleteById(id);
            status= "1";

        }catch (Exception e){
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }
    @ResponseBody
    @PostMapping("/productAdd")
    public CategoryProduct add(@RequestBody CategoryProduct categoryProduct){
        CategoryProduct cp= cpRepo.save(categoryProduct);
        return cp;
    }

    @ResponseBody
    @GetMapping("/productList")
    public List<CategoryProduct> productList(){
        return cpRepo.findAll();
    }

    @ResponseBody
    @DeleteMapping(value = "/productDelete/{stId}")
    public String productDelete(@PathVariable String stId) {
        String status = "0";
        try{
            int id = Integer.parseInt(stId);
            cpRepo.deleteById(id);
            status= "1";

        }catch (Exception e){
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }

}
