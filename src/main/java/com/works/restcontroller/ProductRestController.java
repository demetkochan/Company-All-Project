package com.works.restcontroller;

import com.works.dto.ProductDto;
import com.works.entities.Product;
import com.works.entities.ProductImageInterLayer;
import com.works.repositories.ProductRepository;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/product_rest")
@Api(value ="ProductRestController",authorizations ={@Authorization(value = "basicAuth")})

public class ProductRestController {
    final ProductDto productDto;
    final ProductRepository pRepo;

    public ProductRestController(ProductDto productDto, ProductRepository pRepo) {
        this.productDto = productDto;
        this.pRepo = pRepo;
    }

    @ApiOperation("Ürün veri ekleme")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Product product, BindingResult bResult){

        return productDto.Productadd(product,bResult);
    }

    @ApiOperation("Ürün veri listeleme")
    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return productDto.productlist();
    }

    @ApiOperation("Ürün veri silme")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return productDto.productdelete(strIndex);
    }

    @ApiOperation("Ürün Kategori listeleme")
    @GetMapping("/categoryList")
    public Map<ERest,Object> categoryList(){
        return productDto.categorylist();
    }

    //Ürüne Resim Ekleme
    @ApiOperation("Ürüne resim ekleme")
    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(@RequestParam("product_image") MultipartFile file, ProductImageInterLayer productImage) {
        return productDto.uploadImage(file,productImage);
    }
    //Ürün Resimlerini Listeleme
    @ApiOperation("Ürün resimlerini listeleme")
    @GetMapping("/productImagelist/{strIndex}")
    public Map<ERest ,Object> productImagelist(@PathVariable String strIndex){
        return productDto.productImageList(strIndex);
    }
    //Ürün Resimleri Silme
    @ApiOperation("Ürün resimlerini silme")
    @DeleteMapping("/Imagedelete/{strIndex}")
    public Map<ERest, Object> Imagedelete(@PathVariable String strIndex){
        return productDto.productImageDelete(strIndex);
    }

    @PutMapping("/update")
    @ApiOperation("Ürün veri güncelleme")
    public Map<ERest, Object> update(@RequestBody @Valid Product product,BindingResult bindingResult) {
        return productDto.productUpdate(product,bindingResult);
    }

    //Ürüne Beğeni Verme
    @ApiOperation("Ürüne beğeni verme")
    @PutMapping("/like/{stId}")
    public void productLike(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        pRepo.likeStatus(cid);
    }

    //Ürüne dislike
    @ApiOperation("Ürüne beğeni vermeme")
    @PutMapping("/dislike/{stId}")
    public void productDislike(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        pRepo.dislikeStatus(cid);
    }

}
