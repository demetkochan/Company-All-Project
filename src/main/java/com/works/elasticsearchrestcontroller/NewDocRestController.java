package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.NewsDocDto;
import com.works.models.AdvertisingDoc;
import com.works.models.NewsDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/newsdoc_rest")
public class NewDocRestController {
    final NewsDocDto nDto;

    public NewDocRestController(NewsDocDto nDto) {
        this.nDto = nDto;
    }

    @ApiOperation("Haber Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody NewsDoc nDoc){
        return nDto.add(nDoc);
    }

    @ApiOperation("Haber Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return nDto.list();
    }

    @ApiOperation("Haber Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return nDto.delete(strIndex);
    }

    @ApiOperation("Haber Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){
        return nDto.search(data);
    }
}
