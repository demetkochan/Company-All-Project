package com.works.controller;

import com.works.entities.*;
import com.works.repositories.CategoryAnnouncementRepository;
import com.works.repositories.CategoryGalleryRepository;
import com.works.repositories.CategoryProductRepository;
import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category_mvc")
public class CategoryController {

    private static final Logger log=Logger.getLogger(CategoryController.class);

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
        model.addAttribute("categoryG",new CategoryGallery());
        return "category";
    }
    CategoryAnnouncement announcementUpdate=new CategoryAnnouncement();
    CategoryGallery galleryUpdate=new CategoryGallery();
    CategoryProduct productUpdate=new CategoryProduct();
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
            log.error("Eklem veya güncelleme hatası");
            System.err.println("İşlem sırasında hata oluştur!");
        }

        return announcementUpdate;

    }
/*    @ResponseBody
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

    }*/

    @ResponseBody
    @GetMapping("/announcementList")
    public List<CategoryAnnouncement> list(){
        return caRepo.findAll();
    }

    @ResponseBody
    @DeleteMapping(value = "/newsDelete/{stId}")
    public String delete(@PathVariable String stId) {
        String status = "0";
        try{
            int id = Integer.parseInt(stId);
            caRepo.deleteById(id);
            status= "1";

        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }

    @ResponseBody
    @PostMapping("/galleryAdd")
    public CategoryGallery add(@Valid @RequestBody @ModelAttribute("categoryG") CategoryGallery categoryGallery, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            try {
                if (galleryUpdate.getId() != null && galleryUpdate.getId() > 0) {
                    categoryGallery.setId(galleryUpdate.getId());
                }
                cgRepo.saveAndFlush(categoryGallery);
                galleryUpdate = new CategoryGallery();

            } catch (Exception ex) {
                log.error("Galeri Kategorisi ekleme veya güncelleme hatası");
                System.err.println("İşlem sırasında hata oluştur!");
            }
            log.error("Galeri Kategorisi ekleme veya güncelleme hatası");
        }else{
            log.error("Galeri Kategorisi ekleme veya güncelleme hatası");
            System.err.println("İşlem sırasında hata oluştur!");
        }
        return galleryUpdate;


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
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }
    @ResponseBody
    @PostMapping("/productAdd")
    public CategoryProduct add(@RequestBody CategoryProduct categoryProduct){
        try{
            if(productUpdate.getId() != null && productUpdate.getId() > 0){
                categoryProduct.setId(productUpdate.getId());
            }
            cpRepo.saveAndFlush(categoryProduct);
            productUpdate = new CategoryProduct();

        }catch (Exception ex){
            log.error("Ürün ekleme ve güncelleme hatası");
            System.err.println("İşlem sırasında hata oluştur!");
        }

        return productUpdate;

    }
    /*
    @ResponseBody
    @PostMapping("/productSearch/{StrPro}")
    public List<CategoryProduct> search(String StrPro){
        List<CategoryProduct> psearchList=cpRepo.findByProduct_categoryNameContainsAllIgnoreCase(StrPro);
        return psearchList;

    }
    @ResponseBody
    @PostMapping("/newsSearch/{StrNew}")
    public List<CategoryAnnouncement> newsearch(String StrNew){
        List<CategoryAnnouncement> nsearchList=caRepo.findByNews_categoryNameAllIgnoreCase(StrNew);
        return nsearchList;

    }
*/


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
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }


    @ResponseBody
    @GetMapping("/searchGallery/{data}")
    public List<CategoryGallery> cgsearch(@PathVariable String data) {
        List<CategoryGallery> ls = cgRepo.findByGallerycategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data);
        System.out.println(ls);
        return ls;
    }

    @ResponseBody
    @GetMapping("/searchNews/{data}")
    public List<CategoryAnnouncement> nsearch(@PathVariable String data) {
        List<CategoryAnnouncement> ls = caRepo.findByNewscategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data);
        System.out.println(ls);
        return ls;
    }

    @ResponseBody
    @GetMapping("/searchProduct/{data}")
    public List<CategoryProduct> psearch(@PathVariable String data) {
        List<CategoryProduct> ls = cpRepo.findByProductcategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data);
        System.out.println(ls);
        return ls;
    }

}
