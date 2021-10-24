package com.works.controller;

import com.works.entities.Content;
import com.works.entities.Customer;
import com.works.models.ContentDoc;
import com.works.models.CustomerDoc;
import com.works.repositories.ContentDocRepository;
import com.works.repositories.ContentRepository;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/content_mvc")
public class ContentController {

    private static final Logger log=Logger.getLogger(ContentController.class);
    final ContentRepository cRepo;
    final ContentDocRepository cdRepo;

    public ContentController(ContentRepository cRepo, ContentDocRepository cdRepo) {
        this.cRepo = cRepo;
        this.cdRepo = cdRepo;
    }
    Integer searchSize;

    @GetMapping("")
    public String content(Model model){
        return "content";
    }

    Content contentUpdate = new Content();

    //içerik Ekleme
    @ResponseBody
    @PostMapping("/add")
    public Content contentAdd(@RequestBody Content content){

        try{
            if(contentUpdate.getId() != null && contentUpdate.getId() > 0){
                content.setId(contentUpdate.getId());
            }
            cRepo.saveAndFlush(content);
            contentUpdate = new Content();

        }catch (Exception ex){
            log.error("İçerik ekleme hatası");
            System.err.println("İşlem sırasında hata oluştur!");
        }

        return contentUpdate;

    }


    



    @ResponseBody
    @DeleteMapping(value = "/delete/{stid}")
    public String delete(@PathVariable String stid) {
        String status = "0";
        try{
            int cid = Integer.parseInt(stid);
            cRepo.deleteById(cid);
            status= "1";

        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }

    @ResponseBody
    @GetMapping("/selectProcess/{stPr}")
    public List<Content> selectProcess(Model model, @PathVariable String stPr){
        int pr = Integer.parseInt(stPr);
        List<Content> selectprocess = cRepo.process(pr);
        model.addAttribute("selectProcess",selectprocess);
        return selectprocess;

    }

    @ResponseBody
    @GetMapping("/contentList/{pageNo}/{stpageSize}")
    public List<Content> customerList(@PathVariable String pageNo, @PathVariable String stpageSize ){
        int ipageNumber = Integer.parseInt(pageNo);
        int pageSize = Integer.parseInt(stpageSize);

        if (pageSize == -1){
            List<Content> lsx = new ArrayList<>();
            Iterable<Content> page = cRepo.findAll();
            List<Content> cList = cRepo.findAll();
            cdRepo.deleteAll();
            for(Content item : page){
                lsx.add(item);
            }
            cList.forEach(item ->{
                ContentDoc contentDoc = new ContentDoc();
                contentDoc.setId(item.getId().toString());
                contentDoc.setContenttitle(item.getContenttitle());
                contentDoc.setContent_desc(item.getContent_desc());
                contentDoc.setContent_detail_desc(item.getContent_detail_desc());
                contentDoc.setContent_date(item.getContent_date());
                contentDoc.setContent_status(item.getContent_status());


                cdRepo.save(contentDoc);

            });
            Collections.reverse(lsx);
            return lsx;

        }else {
            Pageable pageable = PageRequest.of(ipageNumber,pageSize);
            Slice<Content> pageList = cRepo.findByOrderByIdAsc(pageable);
            List<Content> ls = pageList.getContent();
            List<Content> contentList = cRepo.findAll();
            cdRepo.deleteAll();
            for(Content item : contentList ) {
                ContentDoc contentDoc = new ContentDoc();
                contentDoc.setId(item.getId().toString());
                contentDoc.setContenttitle(item.getContenttitle());
                contentDoc.setContent_desc(item.getContent_desc());
                contentDoc.setContent_detail_desc(item.getContent_detail_desc());
                contentDoc.setContent_date(item.getContent_date());
                contentDoc.setContent_status(item.getContent_status());


                cdRepo.save(contentDoc);
            }
            return  ls;
        }


    }
    @ResponseBody
    @GetMapping("/search/{pageNo}/{stpageSize}/{data}")
    public List<ContentDoc> contentSearch(@PathVariable String data, @PathVariable int pageNo, @PathVariable int stpageSize ){

        Page<ContentDoc> pages = cdRepo.findByTitle(data, PageRequest.of(pageNo,stpageSize));
        List<ContentDoc> list = pages.getContent();
        List<ContentDoc> listc =  cdRepo.find(data);
        searchSize = listc.size();
        return  list;

    }

    @ResponseBody
    @GetMapping("/contentList/pageCount/{stpageSize}/{stPageStatus}")
    public Integer pageCount(@PathVariable String stpageSize,@PathVariable String stPageStatus){
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if(pageStatus == 1) {
            dataCount = cRepo.count();
        }
        else {
            dataCount = searchSize;
        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stpageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;

    }



}
