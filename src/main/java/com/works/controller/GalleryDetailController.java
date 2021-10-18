package com.works.controller;

import com.works.entities.*;
import com.works.repositories.GalleryImageRepository;
import com.works.repositories.GalleryRepository;
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
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/galleryDetail_mvc")
public class GalleryDetailController {
    private static final Logger log=Logger.getLogger(GalleryDetailController.class);


    final GalleryRepository gRepo;
    final GalleryImageRepository giRepo;
    final private String UPLOAD_DIR="src/main/resources/static/uploads/";

    public GalleryDetailController(GalleryRepository gRepo, GalleryImageRepository giRepo) {
        this.gRepo = gRepo;
        this.giRepo = giRepo;
    }


    @GetMapping("")
    public String galleryDetail(Model model){
        model.addAttribute("galeries",gRepo.findAll());
        model.addAttribute("galleryImage",new GalleryImageInterlayer());

        return "galleryDetail";
    }

    @PostMapping("/imageUpload")
    public String resultAdd(@RequestParam("gallery_image") MultipartFile file, @ModelAttribute("galleryImage") GalleryImageInterlayer galleryImage){

        File theDir = new File(UPLOAD_DIR + "gallery/" + galleryImage.getG_id());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = fileName.substring(fileName.length()-5, fileName.length());
        System.out.println(ext);
        String uui = UUID.randomUUID().toString();
        fileName = uui + ext;
        try {
            Path path = Paths.get(UPLOAD_DIR + "gallery/" + galleryImage.getG_id()+ "/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Ürün resmi ekleme hatasıdır.");
            e.printStackTrace();
        }

        GalleryImage galleryImage1 = new GalleryImage();

        galleryImage1.setGallery_image(fileName);
        galleryImage1.setImage_desc(galleryImage.getImage_desc());
        galleryImage1.setG_id(galleryImage.getG_id());


        giRepo.save(galleryImage1);

        return "redirect:/galleryDetail_mvc";
    }

}
