package com.works.elasticsearchrestcontroller;


import com.works.elasticsearchdto.CategoryProductDocDto;
import com.works.models.CategoryProductDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/catproductdoc_rest")
public class CategoryProductDocRestController {
    final CategoryProductDocDto cpDto;

    public CategoryProductDocRestController(CategoryProductDocDto cpDto) {
        this.cpDto = cpDto;
    }


    @ApiOperation("Ürün Kategorisi Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody CategoryProductDoc cpdoc){
        return cpDto.add(cpdoc);
    }

    @ApiOperation("Ürün Kategori Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return cpDto.list();
    }

    @ApiOperation("Ürün Kategori Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return cpDto.delete(strIndex);
    }

    @ApiOperation("Ürün Kategori Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){

        return cpDto.search(data);
    }
}
