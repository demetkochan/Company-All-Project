package com.works.restcontroller;

import com.works.dto.AdvertisingDto;
import com.works.entities.AdvertisingInterLayer;
import com.works.entities.NewsInterLayer;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/advertising_rest")
@Api(value ="AdvertisingRestController",authorizations ={@Authorization(value = "basicAuth")})
public class AdvertisingRestController {

    final AdvertisingDto aDto;

    public AdvertisingRestController(AdvertisingDto aDto) {
        this.aDto = aDto;
    }

    @ApiOperation("Reklam veri ekleme")
    @PostMapping("/upload")
    public Map<ERest, Object> upload(@RequestParam("imageName") MultipartFile file, AdvertisingInterLayer advertising) {
        return aDto.upload(file,advertising);

    }

    @ApiOperation("Reklam veri listeleme")
    @GetMapping("/advList")
    public Map<ERest,Object> advList(){
        return aDto.advList();
    }

    @ApiOperation("Reklam veri silme")
    @DeleteMapping("/advDelete/{strlid}")
    public Map<ERest, Object> advDelete (@PathVariable String strlid) {
        return aDto.advDelete(strlid);
    }

}
