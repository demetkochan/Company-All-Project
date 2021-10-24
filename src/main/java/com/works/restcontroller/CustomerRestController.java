package com.works.restcontroller;

import com.works.dto.CustomerDto;
import com.works.entities.Customer;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/customer_rest")
@Api(value ="CustomerRestController",authorizations ={@Authorization(value = "basicAuth")})

public class CustomerRestController {
 final CustomerDto cDto;

    public CustomerRestController(CustomerDto cDto) {
        this.cDto = cDto;
    }

    @ApiOperation("Müşteri veri ekleme")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid Customer customer, BindingResult bResult){

        return cDto.customerAdd(customer,bResult);
    }

    @ApiOperation("Müşteri veri listeleme")
    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return cDto.customerList();
    }


    @ApiOperation("Müşteri veri silme")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return cDto.customerDelete(strIndex);
    }

}
