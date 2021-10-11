package com.works.restcontroller;

import com.works.dto.CategoryDto;
import com.works.entities.CategoryAnnouncement;
import com.works.entities.CategoryGallery;
import com.works.entities.CategoryProduct;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/category_rest")
public class CategoryRestController {
final CategoryDto cDto;

    public CategoryRestController(CategoryDto cDto) {
        this.cDto = cDto;
    }
    @PostMapping("/galleryAdd")
    public Map<ERest, Object> galleryAdd(@RequestBody @Valid CategoryGallery gallery, BindingResult bResult){
        return cDto.CategoryGalleryAdd(gallery,bResult);
    }
    @PostMapping("/productAdd")
    public Map<ERest, Object> productAdd(@RequestBody @Valid CategoryProduct product, BindingResult bResult){
        return cDto.CategoryProductAdd(product,bResult);
    }
    @PostMapping("/newsAdd")
    public Map<ERest, Object> newsAdd(@RequestBody @Valid CategoryAnnouncement announcement, BindingResult bResult){
        return cDto.CategoryAnnouncementAdd(announcement,bResult);
    }

    @GetMapping("/newsList")
    public Map<ERest ,Object> newslist(){
        return cDto.announcementlist();
    }
    @GetMapping("/galleryList")
    public Map<ERest ,Object> gallerylist(){
        return cDto.gallerylist();
    }
    @GetMapping("/productList")
    public Map<ERest ,Object> productlist(){
        return cDto.productlist();
    }

    @DeleteMapping("/galleryDelete/{strIndex}")
    public Map<ERest, Object> galleryDelete(@PathVariable String strIndex){
        return cDto.Gallerydelete(strIndex);
    }
    @DeleteMapping("/productDelete/{strIndex}")
    public Map<ERest, Object> productDelete(@PathVariable String strIndex){
        return cDto.Productdelete(strIndex);
    }
    @DeleteMapping("/newsDelete/{strIndex}")
    public Map<ERest, Object> newsDelete(@PathVariable String strIndex){
        return cDto.announcementdelete(strIndex);
    }
}
