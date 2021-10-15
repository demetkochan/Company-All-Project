package com.works.controller;


import com.works.entities.Advertising;
import com.works.entities.AdvertisingInterLayer;
import com.works.entities.Product;
import com.works.repositories.AdvertisingRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/advertising_mvc")
public class AdvertisingManagementController {
    private static final Logger log=Logger.getLogger(AdvertisingManagementController.class);

    final private String UPLOAD_DIR="src/main/resources/static/uploads/advertesing/";
    final AdvertisingRepository aRepo;

    public AdvertisingManagementController(AdvertisingRepository aRepo) {
        this.aRepo = aRepo;
    }

    @GetMapping("")
    public String advertising(Model model){
        model.addAttribute("advertising",new AdvertisingInterLayer());
        return "advertisingManagement";
    }


    @PostMapping("/imageUpload")
    public String resultAdd(@RequestParam("imageName") MultipartFile file, @ModelAttribute("advertising")AdvertisingInterLayer advertising){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = fileName.substring(fileName.length()-5, fileName.length());
        System.out.println(ext);
        String uui = UUID.randomUUID().toString();
        fileName = uui + ext;
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            System.out.println(fileName);
            System.out.println(path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Reklam resmi ekleme hatasıdır.");
            e.printStackTrace();
        }


        Advertising ad=new Advertising();

        ad.setImageName(fileName);
        ad.setAdvtitle(advertising.getAdvtitle());
        ad.setScreentime(advertising.getScreentime());
        ad.setHeight(advertising.getHeight());
        ad.setWidth(advertising.getWidth());
        ad.setStarttime(advertising.getStarttime());
        ad.setEndtime(advertising.getEndtime());
        ad.setLink(advertising.getLink());

        aRepo.save(ad);

        return "redirect:/advertising_mvc";
    }

    @ResponseBody
    @GetMapping("/advList")
    public List<Advertising> advList(){

        return aRepo.findAll();
    }

    @ResponseBody
    @DeleteMapping("/advDelete/{stid}")
    public String advDelete(@PathVariable String stid){
        String status = "0";
        try {
            int lid = Integer.parseInt(stid);
            aRepo.deleteById(lid);

            status = "1";
        }catch (Exception ex) {
            log.error("Silme hatası oluştu.");
            System.err.println("Silme işlemi sırasında bir hata oluştu!");
        }
        return status;

    }

    @ResponseBody
    @GetMapping("/search/{data}")
    public List<Advertising> search(@PathVariable String data) {
        List<Advertising> ls = aRepo.findByAdvtitleContainsIgnoreCaseAllIgnoreCaseOrderByIdAsc(data);
        System.out.println(ls);
        return ls;
    }


}
