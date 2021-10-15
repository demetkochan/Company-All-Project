package com.works.controller;

import com.works.entities.NewsInterLayer;
import com.works.entities.ProductImage;
import com.works.entities.ProductImageInterLayer;
import com.works.entities.ProductsImages;
import com.works.repositories.ProductImageRepository;
import com.works.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/uploadImage_mvc")
public class UploadImageController {

    private static final Logger log=Logger.getLogger(UploadImageController.class);

    final ProductRepository pRepo;
    final ProductImageRepository prRepo;

    final private String UPLOAD_DIR="src/main/resources/static/uploads/";

    public UploadImageController(ProductRepository pRepo, ProductImageRepository prRepo) {
        this.pRepo = pRepo;
        this.prRepo = prRepo;
    }

    @GetMapping("")
    public String uploadImage(Model model){
        model.addAttribute("products",pRepo.findAll());
        model.addAttribute("productImage",new ProductImageInterLayer());
        return "uploadImage";
    }

    @PostMapping("/imageUpload")
    public String resultAdd(@RequestParam("product_image") MultipartFile file, @ModelAttribute("productImage") ProductImageInterLayer productImage){

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
            log.error("Ürün resmi ekleme hatasıdır.");
            e.printStackTrace();
        }

        ProductImage productImage1 = new ProductImage();

        productImage1.setProduct_image(fileName);
        productImage1.setProduct_id(productImage.getProduct_id());


        prRepo.save(productImage1);

        return "redirect:/uploadImage_mvc";
    }

    @ResponseBody
    @GetMapping("/productImage/{stId}")
    public List<ProductsImages> productsImagesList(Model model, @PathVariable String stId){

        int id = Integer.parseInt(stId);
        List<ProductsImages> productsImagesList = prRepo.productsImagesList(id);
        model.addAttribute("images",productsImagesList);

        return productsImagesList;

    }

    //resim silme
    @ResponseBody
    @DeleteMapping(value = "/delete/{stid}")
    public String delete(@PathVariable String stid) {
        String status = "0";
        try{
            int pid = Integer.parseInt(stid);
            prRepo.deleteById(pid);
            status= "1";

        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }


}
