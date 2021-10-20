package com.works.elasticsearchrestcontroller;

import com.works.elasticsearchdto.AnnouncementDocDto;
import com.works.models.AnnouncementDoc;
import com.works.util.ERest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/announcementdoc_rest")
public class AnnouncementDocRestController {
    final AnnouncementDocDto aDto;

    public AnnouncementDocRestController(AnnouncementDocDto aDto) {
        this.aDto = aDto;
    }

    @ApiOperation("Duyuru Ekleme (Elasticsearch)")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody AnnouncementDoc aDoc){
        return aDto.add(aDoc);
    }

    @ApiOperation("Duyuru Listesi (Elasticsearch)")
    @GetMapping("/list")
    public Map<ERest, Object> list(){
        return aDto.list();
    }

    @ApiOperation("Duyuru Silme (Elasticsearch)")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return aDto.delete(strIndex);
    }

    @GetMapping("/search/{data}")
    public Map<ERest, Object> search(@PathVariable String data){

        return aDto.search(data);
    }
}
