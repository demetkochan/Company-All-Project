package com.works.restcontroller;

import com.works.dto.CategoryDto;
import com.works.entities.CategoryAnnouncement;
import com.works.entities.CategoryGallery;
import com.works.entities.CategoryProduct;
import com.works.entities.Content;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/category_rest")
@Api(value ="CategoryRestController",authorizations ={@Authorization(value = "basicAuth")})

public class CategoryRestController {
final CategoryDto cDto;

    public CategoryRestController(CategoryDto cDto) {
        this.cDto = cDto;
    }

    @ApiOperation("Galeri Kategori veri ekleme")
    @PostMapping("/galleryAdd")
    public Map<ERest, Object> galleryAdd(@RequestBody @Valid CategoryGallery gallery, BindingResult bResult){
        return cDto.CategoryGalleryAdd(gallery,bResult);
    }

    @ApiOperation("Ürün Kategori veri ekleme")
    @PostMapping("/productAdd")
    public Map<ERest, Object> productAdd(@RequestBody @Valid CategoryProduct product, BindingResult bResult){
        return cDto.CategoryProductAdd(product,bResult);
    }

    @ApiOperation("Duyuru/Haber Kategori veri ekleme")
    @PostMapping("/newsAdd")
    public Map<ERest, Object> newsAdd(@RequestBody @Valid CategoryAnnouncement announcement, BindingResult bResult){
        return cDto.CategoryAnnouncementAdd(announcement,bResult);
    }

    @ApiOperation("Duyuru/Haber Kategori veri listeleme")
    @GetMapping("/newsList")
    public Map<ERest ,Object> newslist(){
        return cDto.announcementlist();
    }

    @ApiOperation("Galeri Kategori veri listeleme")
    @GetMapping("/galleryList")
    public Map<ERest ,Object> gallerylist(){
        return cDto.gallerylist();
    }

    @ApiOperation("Ürün Kategori veri listeleme")
    @GetMapping("/productList")
    public Map<ERest ,Object> productlist(){
        return cDto.productlist();
    }

    @ApiOperation("Galeri Kategori veri silme")
    @DeleteMapping("/galleryDelete/{strIndex}")
    public Map<ERest, Object> galleryDelete(@PathVariable String strIndex){
        return cDto.Gallerydelete(strIndex);
    }

    @ApiOperation("Ürün Kategori veri Silme")
    @DeleteMapping("/productDelete/{strIndex}")
    public Map<ERest, Object> productDelete(@PathVariable String strIndex){
        return cDto.Productdelete(strIndex);
    }

    @ApiOperation("Duyuru/Haber Kategori veri silme")
    @DeleteMapping("/newsDelete/{strIndex}")
    public Map<ERest, Object> newsDelete(@PathVariable String strIndex){
        return cDto.announcementdelete(strIndex);
    }


    @ApiOperation("Ürün Kategori veri güncelleme")
    @PutMapping("/updateCategoryProduct")
    public Map<ERest, Object> cpupdate(@RequestBody @Valid CategoryProduct categoryProduct, BindingResult bindingResult) {
        return cDto.categoryProductUpdate(categoryProduct,bindingResult);
    }

    @ApiOperation("Galeri Kategori veri güncelleme")
    @PutMapping("/updateCategoryGallery")
    public Map<ERest, Object> cgupdate(@RequestBody @Valid CategoryGallery categoryGallery, BindingResult bindingResult) {
        return cDto.categoryGalleryUpdate(categoryGallery,bindingResult);
    }

    @ApiOperation("Duyuru/Haber Kategori veri güncelleme")
    @PutMapping("/updateCategoryAnnouncement")
    public Map<ERest, Object> caupdate(@RequestBody @Valid CategoryAnnouncement categoryAnnouncement, BindingResult bindingResult) {
        return cDto.categoryAnnouncementUpdate(categoryAnnouncement,bindingResult);
    }
}
