package com.works.dto;

import com.works.entities.*;
import com.works.repositories.CategoryProductRepository;
import com.works.repositories.ProductImageRepository;
import com.works.util.ERest;
import com.works.util.Util;
import com.works.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class ProductDto {
    final ProductRepository pRepo;
    final ProductImageRepository pıRepo;

    final Util util;
    final CategoryProductRepository cgRepo;


    final private String UPLOAD_DIR="src/main/resources/static/uploads/";

    public ProductDto(ProductRepository pRepo, ProductImageRepository pıRepo, Util util, CategoryProductRepository cgRepo) {
        this.pRepo = pRepo;
        this.pıRepo = pıRepo;
        this.util = util;
        this.cgRepo =  cgRepo;
    }

    //ürün ekleme
    public Map<com.works.util.ERest, Object> Productadd(Product product, BindingResult bResult) {
        Map<com.works.util.ERest, Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Product product1 = pRepo.save(product);
                hm.put(com.works.util.ERest.status, true);
                hm.put(com.works.util.ERest.message, "Ekleme başarılı");
                hm.put(com.works.util.ERest.result, product1);
            } catch (Exception e) {
                hm.put(com.works.util.ERest.status, false);
            }

        }else{
            hm.put(com.works.util.ERest.status,false);
            hm.put(com.works.util.ERest.errors, util.errors(bResult));
        }

        return hm;
    }

    //ürün listeleme
    public Map<ERest,Object> productlist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Product> ls = pRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //ürün Silme
    public Map<ERest, Object>productdelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int pid = Integer.parseInt(strIndex);
        try{
            if(pRepo.existsById(pid)){
                pRepo.deleteById(pid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, pid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, pid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, pid);
        }
        return hm;
    }

    //kategori listeleme
    public Map<ERest,Object> categorylist(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<CategoryProduct> ls = cgRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //ürün güncelleme
    public Map<ERest, Object> productUpdate(Product product,BindingResult bindingResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()){
            if ( product.getId() != null ) {

                Optional<Product> oProduct= pRepo.findById(product.getId());
                if ( oProduct.isPresent() ) {
                    try {
                        pRepo.saveAndFlush(product);
                        hm.put(ERest.status, true);
                        hm.put(ERest.message, "Güncelleme başarılı");
                        hm.put(ERest.result, product);
                    }catch (Exception ex) {
                        hm.put(ERest.status, false);

                        hm.put(ERest.result, product);
                    }

                }else {
                    hm.put(ERest.message, false);
                    hm.put(ERest.status, "Update işlemi sırasında hata oluştu!");
                    hm.put(ERest.result, product);
                }

            }else {
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Update işlemi sırasında hata oluştu!");
                hm.put(ERest.result, product);
            }
        }else {
            hm.put(ERest.status,false);
            hm.put(ERest.errors,util.errors(bindingResult));
        }

        return hm;
    }

    //Ürüne Resim Ekleme
    public Map<String, Object> uploadImage(MultipartFile file, ProductImageInterLayer productImage) {
        int sendSuccessCount = 0;
        String errorMessage = "";
        Map<String, Object> hm = new LinkedHashMap<>();
        System.out.println(productImage);
        if (!file.isEmpty() ) {
            File theDir = new File(UPLOAD_DIR + "products/" + productImage.getProduct_id());
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String ext = fileName.substring(fileName.length()-5, fileName.length());
            System.out.println(ext);
            String uui = UUID.randomUUID().toString();
            fileName = uui + ext;
            try {
                Path path = Paths.get(UPLOAD_DIR + "products/" + productImage.getProduct_id()+ "/" + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ProductImage productImage1 = new ProductImage();

            productImage1.setProduct_image(fileName);
            productImage1.setProduct_id(productImage.getProduct_id());


            pıRepo.save(productImage1);

        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put("status", true);
            hm.put("message", "Yükleme Başarılı");
        }else {
            hm.put("status", false);
            hm.put("message", errorMessage);
        }

        return hm;
    }


    //Ürün Fotoları listeleme
    public Map<ERest,Object> productImageList(String id){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        int pid = Integer.parseInt(id);
        List<ProductsImages> ls = pıRepo.productsImagesList(pid);
        hm.put(ERest.result,ls);
        return hm;
    }

    //Ürün Fotoları Silme
    public Map<ERest, Object>productImageDelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int pid = Integer.parseInt(strIndex);
        try{
            if(pıRepo.existsById(pid)){
                pıRepo.deleteById(pid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "silme başarılı");
                hm.put(ERest.result, pid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, pid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "silme gerçekleşmedi");
            hm.put(ERest.result, pid);
        }
        return hm;
    }














}
