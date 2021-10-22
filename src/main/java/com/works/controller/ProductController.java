package com.works.controller;

import com.works.entities.Product;
import com.works.repositories.CategoryProductRepository;
import com.works.repositories.ProductRepository;
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
@RequestMapping("/product_mvc")
public class ProductController {

    private static final Logger log=Logger.getLogger(ProductController.class);
    Integer searchSize;

    final CategoryProductRepository cRepo;
    final ProductRepository pRepo;


    public ProductController( CategoryProductRepository cRepo, ProductRepository pRepo) {

        this.cRepo = cRepo;
        this.pRepo = pRepo;

    }
    Product productUpdate = new Product();

    @GetMapping("")
    public String product(Model model){
        model.addAttribute("categories",cRepo.findAll());
        return "products";
    }

    //ürün Ekleme

    @ResponseBody
    @PostMapping("/add")
    public Product productAdd(@RequestBody Product product){

        try{
            if(productUpdate.getId() != null && productUpdate.getId() > 0){
                product.setId(productUpdate.getId());
            }
            pRepo.saveAndFlush(product);
            productUpdate = new Product();

        }catch (Exception ex){
            log.error("Ürün ekleme veya günceleme hatasıdır.");
            System.err.println("İşlem sırasında hata oluştur!");
        }

        return productUpdate;

    }

    //like
    @ResponseBody
    @PutMapping("/like/{stId}")
    public void productLike(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        pRepo.likeStatus(cid);
    }

    //dislike
    @ResponseBody
    @PutMapping("/dislike/{stId}")
    public void productDislike(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        pRepo.dislikeStatus(cid);
    }



    //ürün silme
    @ResponseBody
    @DeleteMapping(value = "/delete/{stid}")
    public String delete(@PathVariable String stid) {
        String status = "0";
        try{
            int pid = Integer.parseInt(stid);
            pRepo.deleteById(pid);
            status= "1";

        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }



    @ResponseBody
    @GetMapping("/productList/{pageNumber}/{stPageSize}")
    public List<Product> productList(@PathVariable String pageNumber, @PathVariable String stPageSize){

        int ipageNumber = Integer.parseInt(pageNumber);
        int pageSize = Integer.parseInt(stPageSize);

        if( pageSize == -1) {
            List<Product> lst = new ArrayList<>();
            Iterable<Product> page = pRepo.findAll();
            for (Product item : page){
                lst.add(item);
            }
            Collections.reverse(lst);
            return lst;
        } else {
            Pageable pageable = PageRequest.of(ipageNumber, pageSize);
            Slice<Product> pageList = pRepo.findByOrderByIdDesc(pageable);
            List<Product> list = pageList.getContent();
            return list;
        }


    }
    @ResponseBody
    @GetMapping("/search/{pageNumber}/{stPageSize}/{data}")
    public List<Product> productSearch(@PathVariable String data, @PathVariable int pageNumber, @PathVariable int stPageSize){

        Page<Product> pages = pRepo.findByProductnameContainsIgnoreCaseAllIgnoreCaseOrderByIdDesc(data, PageRequest.of(pageNumber, stPageSize));
        List<Product> list = pages.getContent();
        List<Product> listp = pRepo.findByProductnameContainsIgnoreCaseAllIgnoreCase(data);
        searchSize = listp.size();
        return list;

    }


    @ResponseBody
    @GetMapping("/List/pageCount/{stPageSize}/{stPageStatus}")
    public Integer productpageCount(@PathVariable String stPageSize, @PathVariable String stPageStatus) {
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if (pageStatus == 1) {
            dataCount = pRepo.count();
        }
        else{
            dataCount = searchSize;

        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stPageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;
    }




}
