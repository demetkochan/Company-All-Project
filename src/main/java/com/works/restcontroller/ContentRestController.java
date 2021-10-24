package com.works.restcontroller;

import com.works.dto.ContentDto;
import com.works.entities.Content;
import com.works.entities.Product;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/content_rest")
@Api(value ="ContentRestController",authorizations ={@Authorization(value = "basicAuth")})

public class ContentRestController {

    final ContentDto contentDto;

    public ContentRestController(ContentDto contentDto) {
        this.contentDto = contentDto;
    }

    @ApiOperation("İçerik veri ekleme")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Content content, BindingResult bResult){

        return contentDto.Contentadd(content,bResult);
    }

    @ApiOperation("İçerik veri listeleme")
    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return contentDto.contentlist();
    }

    @ApiOperation("İçerik veri Silme")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return contentDto.Contentdelete(strIndex);
    }


    @ApiOperation("İçerik durumua göre veri listeleme")
    @GetMapping("/contentProcess/{process_id}")
    public Map<ERest,Object> payOutList(@PathVariable String process_id){
        return contentDto.ContentProcess(process_id);
    }

    @ApiOperation("İçerik veri güncelleme")
    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Content content, BindingResult bindingResult) {
        return contentDto.contentUpdate(content,bindingResult);
    }

}
