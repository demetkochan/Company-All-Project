package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.AdvertisingDocDto;
import com.works.models.AdvertisingDoc;
import com.works.models.AnnouncementDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/advertisingdoc_rest")
public class AdvertisingDocRestController {
    final AdvertisingDocDto aDto;

    public AdvertisingDocRestController(AdvertisingDocDto aDto) {
        this.aDto = aDto;
    }

    @ApiOperation("Reklam Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody AdvertisingDoc aDoc){
        return aDto.add(aDoc);
    }

    @ApiOperation("Reklam Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return aDto.list();
    }

    @ApiOperation("Reklam Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return aDto.delete(strIndex);
    }

    @ApiOperation("Reklam Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){
        return aDto.search(data);
    }


}
