package com.works.dto;

import com.works.entities.CategoryProduct;
import com.works.entities.Content;
import com.works.repositories.CategoryProductRepository;
import com.works.util.ERest;
import com.works.util.Util;
import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ProductDto {
    final ProductRepository pRepo;
    final Util util;
    final CategoryProductRepository cgRepo;

    public ProductDto(ProductRepository pRepo, Util util, CategoryProductRepository cgRepo) {
        this.pRepo = pRepo;
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











}
