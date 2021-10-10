package com.works.restcontroller;

import com.works.dto.ContentDto;
import com.works.entities.Content;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/content_rest")
public class ContentRestController {

    final ContentDto contentDto;

    public ContentRestController(ContentDto contentDto) {
        this.contentDto = contentDto;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody @Valid Content content, BindingResult bResult){

        return contentDto.Contentadd(content,bResult);
    }

    @GetMapping("/list")
    public Map<String ,Object> list(){
        return contentDto.contentlist();
    }

    @DeleteMapping("/delete/{strIndex}")
    public Map<String, Object> delete(@PathVariable String strIndex){
        return contentDto.Contentdelete(strIndex);
    }

    @GetMapping("/contentProcess/{process_id}")
    public Map<String ,Object> payOutList(@PathVariable String process_id){
        return contentDto.ContentProcess(process_id);
    }

}
