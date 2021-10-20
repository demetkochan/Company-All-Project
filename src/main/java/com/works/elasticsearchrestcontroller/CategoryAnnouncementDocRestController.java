package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.CategoryAnnouncementDocDto;
import com.works.models.AnnouncementDoc;
import com.works.models.CategoryAnnouncementDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/catannouncementdoc_rest")
public class CategoryAnnouncementDocRestController {
    final CategoryAnnouncementDocDto caDto;

    public CategoryAnnouncementDocRestController(CategoryAnnouncementDocDto caDto) {
        this.caDto = caDto;
    }

    @ApiOperation("Duyuru Kategorisi Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody CategoryAnnouncementDoc adoc){
        return caDto.add(adoc);
    }

    @ApiOperation("Duyuru Kategori Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return caDto.list();
    }

    @ApiOperation("Duyuru Kategori Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return caDto.delete(strIndex);
    }

    @ApiOperation("Duyuru Kategori Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){

        return caDto.search(data);
    }
}
