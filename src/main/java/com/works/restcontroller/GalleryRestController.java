package com.works.restcontroller;


import com.works.dto.GalleryDto;
import com.works.entities.Gallery;
import com.works.entities.GalleryImageInterlayer;
import com.works.entities.ProductImageInterLayer;
import com.works.entities.Survey;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/gallery_rest")
public class GalleryRestController {
    final GalleryDto galleryDto;

    public GalleryRestController(GalleryDto galleryDto) {
        this.galleryDto = galleryDto;
    }

    //Galeri Ekleme
    @PostMapping("/galleryadd")
    public Map<ERest, Object> add(@RequestBody @Valid Gallery gallery, BindingResult bResult){
        return galleryDto.Galeryadd(gallery,bResult);
    }

    //Galeri Listeleme
    @GetMapping("/galleryList")
    public Map<ERest ,Object> galleryList(){
        return galleryDto.galeryList();
    }

    //Galeri Silme
    @DeleteMapping("/gallerydelete/{strIndex}")
    public Map<ERest, Object> gallerydelete(@PathVariable String strIndex){
        return galleryDto.gallerydelete(strIndex);
    }

    //Galeri Güncelleme
    @PutMapping("/galleryupdate")
    public Map<ERest, Object> galleryupdate(@RequestBody @Valid Gallery gallery, BindingResult bindingResult) {
        return galleryDto.galleryUpdate(gallery,bindingResult);
    }

    //Galeriye Resim ekleme
    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(@RequestParam("gallery_image") MultipartFile file, GalleryImageInterlayer galleryImage) {
        return galleryDto.uploadImage(file,galleryImage);
    }
    //Galeri resimleri listeleme
    @GetMapping("/galleryImagelist/{strIndex}")
    public Map<ERest ,Object> galleryImagelist(@PathVariable String strIndex){
        return galleryDto.galleryImageList(strIndex);
    }

    //Galeri resim silme
    @DeleteMapping("/Imagedelete/{strIndex}")
    public Map<ERest, Object> Imagedelete(@PathVariable String strIndex){
        return galleryDto.galleryImageDelete(strIndex);
    }


}
