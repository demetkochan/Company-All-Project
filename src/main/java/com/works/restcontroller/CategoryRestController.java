package com.works.restcontroller;

import com.works.dto.CategoryDto;
import com.works.entities.CategoryAnnouncement;
import com.works.entities.CategoryGallery;
import com.works.entities.CategoryProduct;
import com.works.entities.Content;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/category_rest")
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

    @PutMapping("/updateCategoryProduct")
    public Map<ERest, Object> cpupdate(@RequestBody @Valid CategoryProduct categoryProduct, BindingResult bindingResult) {
        return cDto.categoryProductUpdate(categoryProduct,bindingResult);
    }
    @PutMapping("/updateCategoryGallery")
    public Map<ERest, Object> cgupdate(@RequestBody @Valid CategoryGallery categoryGallery, BindingResult bindingResult) {
        return cDto.categoryGalleryUpdate(categoryGallery,bindingResult);
    }
    @PutMapping("/updateCategoryAnnouncement")
    public Map<ERest, Object> caupdate(@RequestBody @Valid CategoryAnnouncement categoryAnnouncement, BindingResult bindingResult) {
        return cDto.categoryAnnouncementUpdate(categoryAnnouncement,bindingResult);
    }
}
