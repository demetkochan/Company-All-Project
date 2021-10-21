package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.ProductDocDto;
import com.works.models.AdvertisingDoc;
import com.works.models.ProductDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/productdoc_rest")
public class ProductDocRestController {
    final ProductDocDto pDto;

    public ProductDocRestController(ProductDocDto pDto) {
        this.pDto = pDto;
    }

    @ApiOperation("Ürün Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody ProductDoc pDoc){
        return pDto.add(pDoc);
    }

    @ApiOperation("Ürün Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return pDto.list();
    }

    @ApiOperation("Ürün Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return pDto.delete(strIndex);
    }

    @ApiOperation("Ürün Arama (Elasticsearch)")
    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){
        return pDto.search(data);
    }

}
