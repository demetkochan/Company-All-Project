package com.works.controller;

import com.works.dto.ProductDto;
import com.works.entities.Product;
import com.works.repositories.CategoryProductRepository;
import com.works.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product_mvc")
public class ProductController {

    private static final Logger log=Logger.getLogger(ProductController.class);

    final ProductDto productDto;
    final CategoryProductRepository cRepo;
    final ProductRepository pRepo;


    public ProductController(ProductDto productDto, CategoryProductRepository cRepo, ProductRepository pRepo) {
        this.productDto = productDto;
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


    //ürün Listeleme
    @ResponseBody
    @GetMapping("/list")
    public List<Product> list(){
        return pRepo.findAll();
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
    @GetMapping("/search/{data}")
    public List<Product> search(@PathVariable String data) {
        List<Product> ls = pRepo.findByProductnameContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data);
        System.out.println(ls);
        return ls;
    }


}
