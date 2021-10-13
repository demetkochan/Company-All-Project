package com.works.restcontroller;

import com.works.dto.AdvertisingDto;
import com.works.entities.AdvertisingInterLayer;
import com.works.entities.NewsInterLayer;
import com.works.util.ERest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/advertising_rest")
public class AdvertisingRestController {

    final AdvertisingDto aDto;

    public AdvertisingRestController(AdvertisingDto aDto) {
        this.aDto = aDto;
    }

    @PostMapping("/upload")
    public Map<ERest, Object> upload(@RequestParam("imageName") MultipartFile file, AdvertisingInterLayer advertising) {
        return aDto.upload(file,advertising);

    }

    @GetMapping("/advList")
    public Map<ERest,Object> advList(){
        return aDto.advList();
    }

    @DeleteMapping("/advDelete/{strlid}")
    public Map<ERest, Object> advDelete (@PathVariable String strlid) {
        return aDto.advDelete(strlid);
    }

}
