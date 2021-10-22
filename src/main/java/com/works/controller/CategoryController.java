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
import org.springframework.web.bind.annotation.*;

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
    //category announcement start
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
            Slice<CategoryAnnouncement> pageList = caRepo.findByOrderByIdAsc(pageable);
            List<CategoryAnnouncement> ls = pageList.getContent();
            return ls;
        }


    }
    @ResponseBody
    @GetMapping("/search/{pageNo}/{stpageSize}/{data}")
    public List<CategoryAnnouncement> contentSearch(@PathVariable String data, @PathVariable int pageNo, @PathVariable int stpageSize ){

        Page<CategoryAnnouncement> pages = caRepo.findByNewscategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data, PageRequest.of(pageNo,stpageSize));
        List<CategoryAnnouncement> list = pages.getContent();
        List<CategoryAnnouncement> listc = caRepo.findByNewscategorynameContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listc.size();
        return  list;

    }


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
    //category announcement end


   //gallery category start
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
    @GetMapping("/galleryList/{pageNumber}/{stPageSize}")
    public List<CategoryGallery> galleryList(@PathVariable String pageNumber, @PathVariable String stPageSize){

        int ipageNumber = Integer.parseInt(pageNumber);
        int pageSize = Integer.parseInt(stPageSize);

        if( pageSize == -1) {
            List<CategoryGallery> lst = new ArrayList<>();
            Iterable<CategoryGallery> page = cgRepo.findAll();
            for (CategoryGallery item : page){
                lst.add(item);
            }
            Collections.reverse(lst);
            return lst;
        } else {
            Pageable pageable = PageRequest.of(ipageNumber, pageSize);
            Slice<CategoryGallery> pageList = cgRepo.findByOrderByIdAsc(pageable);
            List<CategoryGallery> list = pageList.getContent();
            return list;
        }


    }
    @ResponseBody
    @GetMapping("/gallerySearch/{pageNumber}/{stPageSize}/{data}")
    public List<CategoryGallery> gallerySearch(@PathVariable String data, @PathVariable int pageNumber, @PathVariable int stPageSize){

        Page<CategoryGallery> pages = cgRepo.findByGallerycategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data, PageRequest.of(pageNumber, stPageSize));
        List<CategoryGallery> list = pages.getContent();
        List<CategoryGallery> listg = cgRepo.findByGallerycategorynameContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listg.size();
        return  list;

    }


    @ResponseBody
    @GetMapping("/galleryList/pageCount/{stPageSize}/{stPageStatus}")
    public Integer gallerypageCount(@PathVariable String stPageSize, @PathVariable String stPageStatus) {
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if (pageStatus == 1) {
            dataCount = cgRepo.count();
        }
        else{
            dataCount = searchSize;

        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stPageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;
    }
   //gallery category end

    //product category start
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
    @GetMapping("/productList/{pageNumber}/{stPageSize}")
    public List<CategoryProduct> productList(@PathVariable String pageNumber, @PathVariable String stPageSize){

        int ipageNumber = Integer.parseInt(pageNumber);
        int pageSize = Integer.parseInt(stPageSize);

        if( pageSize == -1) {
            List<CategoryProduct> lst = new ArrayList<>();
            Iterable<CategoryProduct> page = cpRepo.findAll();
            for (CategoryProduct item : page){
                lst.add(item);
            }
            Collections.reverse(lst);
            return lst;
        } else {
            Pageable pageable = PageRequest.of(ipageNumber, pageSize);
            Slice<CategoryProduct> pageList = cpRepo.findByOrderByIdAsc(pageable);
            List<CategoryProduct> list = pageList.getContent();
            return list;
        }


    }
    @ResponseBody
    @GetMapping("/productSearch/{pageNumber}/{stPageSize}/{data}")
    public List<CategoryProduct> productSearch(@PathVariable String data, @PathVariable int pageNumber, @PathVariable int stPageSize){

        Page<CategoryProduct> pages = cpRepo.findByProductcategorynameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data, PageRequest.of(pageNumber, stPageSize));
        List<CategoryProduct> list = pages.getContent();
        List<CategoryProduct> listg = cpRepo.findByProductcategorynameContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listg.size();
        return  list;

    }


    @ResponseBody
    @GetMapping("/productList/pageCount/{stPageSize}/{stPageStatus}")
    public Integer productpageCount(@PathVariable String stPageSize, @PathVariable String stPageStatus) {
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if (pageStatus == 1) {
            dataCount = cpRepo.count();
        }
        else{
            dataCount = searchSize;

        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stPageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;
    }
    //product category end



}
