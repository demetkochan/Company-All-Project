package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.CustomerDocDto;
import com.works.models.AnnouncementDoc;
import com.works.models.CustomerDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customerdoc_rest")
public class CustomerDocRestController {
    final CustomerDocDto cDto;

    public CustomerDocRestController(CustomerDocDto cDto) {
        this.cDto = cDto;
    }

    @ApiOperation("Müşteri Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody CustomerDoc cDoc){
        return cDto.add(cDoc);
    }

    @ApiOperation("Müşteri Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return cDto.list();
    }

    @ApiOperation("Müşteri Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return cDto.delete(strIndex);
    }

    @ApiOperation("Müşteri Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){

        return cDto.search(data);
    }
}
