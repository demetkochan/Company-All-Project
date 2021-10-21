package com.works.controller;

import com.works.entities.*;
import com.works.repositories.CategoryAnnouncementRepository;
import com.works.repositories.CategoryGalleryRepository;
import com.works.repositories.CategoryProductRepository;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/category_mvc")
public class CategoryController {

    private static final Logger log=Logger.getLogger(CategoryController.class);
    Integer searchSize;
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

    @ResponseBody
    @GetMapping("/newsList/{pageNo}/{stpageSize}")
    public List<CategoryAnnouncement> newsList(@PathVariable String pageNo, @PathVariable String stpageSize){

        int ipageNumber = Integer.parseInt(pageNo);
        int pageSize = Integer.parseInt(stpageSize);

        if( pageSize == -1) {
            List<CategoryAnnouncement> lsx = new ArrayList<>();
            Iterable<CategoryAnnouncement> page = caRepo.findAll();
           for (CategoryAnnouncement item : page){
                lsx.add(item);
            }
            Collections.reverse(lsx);
            return lsx;
        } else {
            Pageable pageable = PageRequest.of(ipageNumber, pageSize);
            Slice<CategoryAnnouncement> pageList = caRepo.findByOrderByIdDesc(pageable);
            List<CategoryAnnouncement> ls = pageList.getContent();
            return ls;
        }


    }
    @ResponseBody
    @GetMapping("/search/{pageNo}/{stpageSize}/{data}")
    public List<CategoryAnnouncement> contentSearch(@PathVariable String data, @PathVariable int pageNo, @PathVariable int stpageSize ){

        Page<CategoryAnnouncement> pages = caRepo.findByNewscategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdDesc(data, PageRequest.of(pageNo,stpageSize));
        List<CategoryAnnouncement> list = pages.getContent();
        List<CategoryAnnouncement> listc = caRepo.findByNewscategorynameContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listc.size();
        return  list;

    }

    // pageCount - start
    @ResponseBody
    @GetMapping("/List/pageCount/{stpageSize}/{stPageStatus}")
    public Integer pageCount(@PathVariable String stpageSize,@PathVariable String stPageStatus) {
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if (pageStatus == 1) {
            dataCount = caRepo.count();
        }
        else{
            dataCount = searchSize;

        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stpageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;
    }
    // pageCount - end


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
    public CategoryGallery add(@RequestBody CategoryGallery categoryGallery){
       // if(bindingResult.hasErrors()) {
        //    log.error("Galeri Kategorisi ekleme veya güncelleme hatası");
       //     System.err.println("İşlem sırasında hata oluştur!");

      //  }else{

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
      //  }
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
