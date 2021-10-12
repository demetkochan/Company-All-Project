package com.works.restcontroller;

import com.works.dto.ProductDto;
import com.works.entities.Product;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/product_rest")
public class ProductRestController {
    final ProductDto productDto;

    public ProductRestController(ProductDto productDto) {
        this.productDto = productDto;
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Product product, BindingResult bResult){

        return productDto.Productadd(product,bResult);
    }

    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return productDto.productlist();
    }

    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return productDto.productdelete(strIndex);
    }

    @GetMapping("/categoryList")
    public Map<ERest,Object> categoryList(){
        return productDto.categorylist();
    }



    @PutMapping("/update")
    public Map<ERest, Object> update(@RequestBody @Valid Product product,BindingResult bindingResult) {
        return productDto.productUpdate(product,bindingResult);
    }
}
