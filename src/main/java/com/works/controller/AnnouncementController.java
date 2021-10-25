package com.works.controller;

import com.works.entities.*;
import com.works.repositories.AnnouncementRepository;
import com.works.repositories.NewsRepository;
import com.works.services.UtilServices;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/announcement_mvc")
public class AnnouncementController {
    private static final Logger log=Logger.getLogger(AnnouncementController.class);

    final private String UPLOAD_DIR="src/main/resources/static/uploads/";

    final AnnouncementRepository aRepo;
    final NewsRepository nRepo;
    final UtilServices uservice;
    Integer searchSize;
    public AnnouncementController(AnnouncementRepository aRepo, NewsRepository nRepo, UtilServices uservice) {
        this.aRepo = aRepo;
        this.nRepo = nRepo;
        this.uservice = uservice;
    }

    @GetMapping("")
    public String announcement(Model model){
        model.addAttribute("categoryNews",uservice.categoryAnnouncementsList());
        model.addAttribute("news",new NewsInterLayer());
        return "announcement";
    }

    Announcement announcementUpdate = new Announcement();
    //Duyuru ekleme ve güncelleme
    @ResponseBody
    @PostMapping("/add")
    public Announcement add(@RequestBody Announcement announcement){
        //jpa-----
        try{
            if(announcementUpdate.getId() != null && announcementUpdate.getId() > 0){
                announcement.setId(announcementUpdate.getId());
            }
            aRepo.saveAndFlush(announcement);
            announcementUpdate = new Announcement();

        }catch (Exception ex){
            log.error("Duyuru ekleme veya güncelleme hatası");
            System.err.println("İşlem sırasında hata oluştur!");
        }

        return announcementUpdate;
    }

    //Duyuru Listeleme
    @ResponseBody
    @GetMapping("/aList/{pageNumber}/{stPageSize}")
    public List<Announcement> announcementList(@PathVariable String pageNumber, @PathVariable String stPageSize){

        int ipageNumber = Integer.parseInt(pageNumber);
        int pageSize = Integer.parseInt(stPageSize);

        if( pageSize == -1) {
            List<Announcement> lst = new ArrayList<>();
            Iterable<Announcement> page = aRepo.findAll();
            for (Announcement item : page){
                lst.add(item);
            }
            Collections.reverse(lst);
            return lst;
        } else {
            Pageable pageable = PageRequest.of(ipageNumber, pageSize);
            Slice<Announcement> pageList = aRepo.findByOrderByIdAsc(pageable);
            List<Announcement> list = pageList.getContent();
            return list;
        }


    }
    @ResponseBody
    @GetMapping("/asearch/{pageNumber}/{stPageSize}/{data}")
    public List<Announcement> aSearch(@PathVariable String data, @PathVariable int pageNumber, @PathVariable int stPageSize){

        Page<Announcement> pages = aRepo.findByAnnouncementtitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data, PageRequest.of(pageNumber, stPageSize));
        List<Announcement> list = pages.getContent();
        List<Announcement> listp = aRepo.findByAnnouncementtitleContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listp.size();
        return list;

    }


    @ResponseBody
    @GetMapping("/aList/pageCount/{stPageSize}/{stPageStatus}")
    public Integer apageCount(@PathVariable String stPageSize, @PathVariable String stPageStatus) {
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if (pageStatus == 1) {
            dataCount = aRepo.count();
        }
        else{
            dataCount = searchSize;

        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stPageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;
    }



    //Duyuru Silme
    @ResponseBody
    @DeleteMapping(value = "/delete/{stid}")
    public String delete(@PathVariable String stid) {
        String status = "0";
        try{
            int aid = Integer.parseInt(stid);
            aRepo.deleteById(aid);
            status= "1";

        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }

    @ResponseBody
    @GetMapping("/selectedStatus/{stPr}")
    public List<Announcement> selectedStatus(Model model, @PathVariable String stPr){
        int pr = Integer.parseInt(stPr);
        List<Announcement> selectedStatus = aRepo.process(pr);
        model.addAttribute("selectProcess",selectedStatus);
        return selectedStatus;

    }

    //---------------------------------------Haber----------------------------------//

    @PostMapping("/imageUpload")
    public String resultAdd(@RequestParam("news_image") MultipartFile file, @ModelAttribute("news") NewsInterLayer news){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = fileName.substring(fileName.length()-5, fileName.length());
        System.out.println(ext);
        String uui = UUID.randomUUID().toString();
        fileName = uui + ext;
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            System.out.println(fileName);
            System.out.println(path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }


        News news1 = new News();

        news1.setNews_image(fileName);
        news1.setNewstitle(news.getNewstitle());
        news1.setNews_detail_desc(news.getNews_detail_desc());
        news1.setNews_desc(news.getNews_desc());
        news1.setNews_status(news.getNews_status());
        news1.setNews_category(news.getNews_category());

        nRepo.save(news1);

        return "redirect:/announcement_mvc";
    }


    @ResponseBody
    @GetMapping("/newslist")
    public List<News> newsList(){

        return nRepo.findAll();
    }

    @ResponseBody
    @DeleteMapping("/newsdelete/{stid}")
    public String newsdelete(@PathVariable String stid){
        String status = "0";
        try {
            int lid = Integer.parseInt(stid);
            nRepo.deleteById(lid);

            status = "1";
        }catch (Exception ex) {
            log.error("Silme hatası oluştu.");
            System.err.println("Silme işlemi sırasında bir hata oluştu!");
        }
        return status;

    }

    @ResponseBody
    @GetMapping("/selectedCategory/{stPr}")
    public List<News> selectedCategory(Model model, @PathVariable String stPr){
        int pr = Integer.parseInt(stPr);
        List<News> selectedCategory = nRepo.process(pr);
        model.addAttribute("selectProcess",selectedCategory);
        return selectedCategory;

    }

    @ResponseBody
    @GetMapping("/selectedStatusNews/{stPr}")
    public List<News> selectedStatusNews(Model model, @PathVariable String stPr){
        int pr = Integer.parseInt(stPr);
        List<News> selectedStatusNews = nRepo.statusproces(pr);
        model.addAttribute("selectProcess",selectedStatusNews);
        return selectedStatusNews;

    }

    @ResponseBody
    @GetMapping("/nList/{pageNumber}/{stPageSize}")
    public List<News>nList(@PathVariable String pageNumber, @PathVariable String stPageSize){

        int ipageNumber = Integer.parseInt(pageNumber);
        int pageSize = Integer.parseInt(stPageSize);

        if( pageSize == -1) {
            List<News> lst = new ArrayList<>();
            Iterable<News> page = nRepo.findAll();
            for (News item : page){
                lst.add(item);
            }
            Collections.reverse(lst);
            return lst;
        } else {
            Pageable pageable = PageRequest.of(ipageNumber, pageSize);
            Slice<News> pageList = nRepo.findByOrderByIdAsc(pageable);
            List<News> list = pageList.getContent();
            return list;
        }


    }
    @ResponseBody
    @GetMapping("/nsearch/{pageNumber}/{stPageSize}/{data}")
    public List<News> nSearch(@PathVariable String data, @PathVariable int pageNumber, @PathVariable int stPageSize){

        Page<News> pages = nRepo.findByNewstitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data, PageRequest.of(pageNumber, stPageSize));
        List<News> list = pages.getContent();
        List<News> listp = nRepo.findByNewstitleContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listp.size();
        return list;

    }


    @ResponseBody
    @GetMapping("/nList/pageCount/{stPageSize}/{stPageStatus}")
    public Integer npageCount(@PathVariable String stPageSize, @PathVariable String stPageStatus) {
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if (pageStatus == 1) {
            dataCount = nRepo.count();
        }
        else{
            dataCount = searchSize;

        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stPageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;
    }







}
