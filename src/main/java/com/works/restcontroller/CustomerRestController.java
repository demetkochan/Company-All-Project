package com.works.restcontroller;

import com.works.dto.CustomerDto;
import com.works.entities.Customer;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/customer_rest")
public class CustomerRestController {
 final CustomerDto cDto;

    public CustomerRestController(CustomerDto cDto) {
        this.cDto = cDto;
    }

    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Customer customer, BindingResult bResult){

        return cDto.customerAdd(customer,bResult);
    }

    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return cDto.customerList();
    }

    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return cDto.customerDelete(strIndex);
    }

}
