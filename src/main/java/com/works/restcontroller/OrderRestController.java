package com.works.restcontroller;

import com.works.dto.OrderDto;
import com.works.entities.OrderBox;
import com.works.repositories.OrderBoxRepository;
import com.works.util.ERest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/order_rest")
@Api(value ="OrderRestController",authorizations ={@Authorization(value = "basicAuth")})
public class OrderRestController {

    final OrderDto orderDto;
    final OrderBoxRepository obRepo;

    public OrderRestController(OrderDto orderDto, OrderBoxRepository obRepo) {
        this.orderDto = orderDto;
        this.obRepo = obRepo;
    }

    //Sipariş ekleme
    @ApiOperation("Sipariş veri ekleme")
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid OrderBox orderBox, BindingResult bResult){

        return orderDto.orderAdd(orderBox,bResult);
    }

    //Sipariş Silme
    @ApiOperation("Sipariş veri listeleme")
    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return orderDto.orderList();
    }

    //Sipariş Silme
    @ApiOperation("Sipariş veri silme")
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return orderDto.orderDelete(strIndex);
    }

    //Sipariş teslim edilme durumu
    @ApiOperation("Sipariş veri teslim edildi durumuna getirme")
    @PutMapping("/delivered/{stId}")
    public void productDelivered(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        obRepo.orderStatus(cid);

    }

    //Sipariş Hazırlanıyor edilme durumu
    @ApiOperation("Sipariş veri hazırlanıyor durumuna getirme")
    @PutMapping("/loading/{stId}")
    public void productloading(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        obRepo.deliveredStatus(cid);

    }


}
