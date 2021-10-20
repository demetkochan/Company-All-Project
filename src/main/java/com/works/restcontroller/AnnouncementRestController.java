package com.works.restcontroller;

import com.works.dto.AnnouncementDto;
import com.works.entities.Announcement;
import com.works.entities.Content;
import com.works.entities.News;
import com.works.entities.NewsInterLayer;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/announcement_rest")
public class AnnouncementRestController {

    final AnnouncementDto announcementDto;

    public AnnouncementRestController(AnnouncementDto announcementDto) {
        this.announcementDto = announcementDto;
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Announcement announcement, BindingResult bResult){

        return announcementDto.Announcementadd(announcement,bResult);
    }

    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return announcementDto.announcementlist();
    }

    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return announcementDto.Announcementdelete(strIndex);
    }

    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Announcement announcement, BindingResult bindingResult) {
        return announcementDto.announcementUpdate(announcement,bindingResult);
    }
    //----------------------------------------Haber---------------------------------------//

    @PostMapping("/upload")
    public Map<ERest, Object> upload(@RequestParam("news_image") MultipartFile file, NewsInterLayer news) {
        return announcementDto.upload(file,news);

    }

    @GetMapping("/newslist")
    public Map<ERest,Object> newsList(){
        return announcementDto.newsList();
    }

    @DeleteMapping("/newsdelete/{strlid}")
    public Map<ERest, Object> newsdelete (@PathVariable String strlid) {
        return announcementDto.newsdelete(strlid);
    }

    @PutMapping("/updateNews")
    public Map<ERest, Object> update(@RequestBody @Valid News news, BindingResult bindingResult) {
        return announcementDto.newsUpdate(news,bindingResult);
    }

    @GetMapping("/newsProcess/{process_id}")
    public Map<ERest,Object> newsProcess(@PathVariable String process_id){
        return announcementDto.NewsProcess(process_id);
    }

    @GetMapping("/newsCategory/{category_id}")
    public Map<ERest,Object> newsCategory(@PathVariable String category_id){
        return announcementDto.NewsCategory(category_id);
    }

    @GetMapping("/announcementStatus/{process_id}")
    public Map<ERest,Object> announcementStatus(@PathVariable String process_id){
        return announcementDto.AnnouncementStatus(process_id);
    }

}
