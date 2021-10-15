package com.works.dto;

import com.works.entities.Advertising;
import com.works.entities.AdvertisingInterLayer;
import com.works.entities.News;
import com.works.entities.NewsInterLayer;
import com.works.repositories.AdvertisingRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class AdvertisingDto {
    final AdvertisingRepository aRepo;
    final Util util;


    final private String UPLOAD_DIR="src/main/resources/static/uploads/";
    long maxFileUploadSize = 2048;


    public AdvertisingDto(AdvertisingRepository aRepo, Util util) {
        this.aRepo = aRepo;
        this.util = util;
    }

    public Map<ERest, Object> upload(MultipartFile file, AdvertisingInterLayer advertising) {
        int sendSuccessCount = 0;
        String errorMessage = "";
        Map<ERest, Object> hm = new LinkedHashMap<>();
        System.out.println(advertising);
        if (!file.isEmpty() ) {
            long fileSizeMB = file.getSize() / 1024;
            if ( fileSizeMB > maxFileUploadSize ) {
                System.err.println("Dosya boyutu çok büyük Max 2MB");
                errorMessage = "Dosya boyutu çok büyük Max "+ (maxFileUploadSize / 1024) +"MB olmalıdır";
            }else {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String ext = fileName.substring(fileName.length()-5, fileName.length());
                String uui = UUID.randomUUID().toString();
                fileName = uui + ext;
                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    sendSuccessCount += 1;

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

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Yükleme Başarılı");
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, errorMessage);
        }

        return hm;
    }

    //Reklam Listeleme
    public Map<ERest,Object> advList(){
        Map<ERest ,Object> hm=new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Advertising> ls = aRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Reklam Silme
    public Map<ERest, Object> advDelete (String strlid){
        Map<ERest, Object> hm = new HashMap<>();
        int aid = Integer.parseInt(strlid);
        try{
            if(aRepo.existsById(aid)){
                aRepo.deleteById(aid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Silme işlemi başarılı!");
                hm.put(ERest.result, aid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, aid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme işlemi gerçekleşmedi");
            hm.put(ERest.result, aid);
        }
        return hm;
    }

}
