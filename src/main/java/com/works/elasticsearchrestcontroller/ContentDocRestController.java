package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.ContentDocDto;
import com.works.models.AnnouncementDoc;
import com.works.models.ContentDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/contentdoc_rest")
public class ContentDocRestController {
    final ContentDocDto cDto;

    public ContentDocRestController(ContentDocDto cDto) {
        this.cDto = cDto;
    }


    @ApiOperation("İçerik Ekleme Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody ContentDoc cDoc){
        return cDto.add(cDoc);
    }

    @ApiOperation("İçerik Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return cDto.list();
    }

    @ApiOperation("İçerik Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return cDto.delete(strIndex);
    }

    @ApiOperation("İçerik Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){

        return cDto.search(data);
    }
}
