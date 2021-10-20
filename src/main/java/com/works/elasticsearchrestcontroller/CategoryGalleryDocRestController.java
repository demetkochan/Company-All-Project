package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.CategoryGalleryDocDto;
import com.works.models.CategoryAnnouncementDoc;
import com.works.models.CategoryGalleryDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/catgallerydoc_rest")
public class CategoryGalleryDocRestController {
    final CategoryGalleryDocDto cgDto;

    public CategoryGalleryDocRestController(CategoryGalleryDocDto cgDto) {
        this.cgDto = cgDto;
    }


    @ApiOperation("Galeri Kategorisi Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody CategoryGalleryDoc cgdoc){
        return cgDto.add(cgdoc);
    }

    @ApiOperation("Galeri Kategori Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return cgDto.list();
    }

    @ApiOperation("Galeri Kategori Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return cgDto.delete(strIndex);
    }

    @ApiOperation("Galeri Kategori Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){

        return cgDto.search(data);
    }
}
