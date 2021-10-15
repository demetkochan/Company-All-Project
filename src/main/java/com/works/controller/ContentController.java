package com.works.controller;

import com.works.entities.Content;
import com.works.entities.Product;
import com.works.repositories.ContentRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/content_mvc")
public class ContentController {

    private static final Logger log=Logger.getLogger(ContentController.class);
    final ContentRepository cRepo;

    public ContentController(ContentRepository cRepo) {
        this.cRepo = cRepo;
    }


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


    //İçerik Listeleme
    @ResponseBody
    @GetMapping("/list")
    public List<Content> list(){
        return cRepo.findAll();
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
    @GetMapping("/search/{data}")
    public List<Content> search(@PathVariable String data) {
        List<Content> ls = cRepo.findByContenttitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data);
        System.out.println(ls);
        return ls;
    }


}
