package com.works.restcontroller;

import com.works.dto.OrderDto;
import com.works.entities.OrderBox;
import com.works.repositories.OrderBoxRepository;
import com.works.util.ERest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/order_rest")


public class OrderRestController {

    final OrderDto orderDto;
    final OrderBoxRepository obRepo;

    public OrderRestController(OrderDto orderDto, OrderBoxRepository obRepo) {
        this.orderDto = orderDto;
        this.obRepo = obRepo;
    }

    //Sipariş ekleme
    @PostMapping("/add")
    public Map<ERest, Object> add(@RequestBody @Valid OrderBox orderBox, BindingResult bResult){

        return orderDto.orderAdd(orderBox,bResult);
    }

    //Sipariş Silme
    @GetMapping("/list")
    public Map<ERest ,Object> list(){
        return orderDto.orderList();
    }

    //Sipariş Silme
    @DeleteMapping("/delete/{strIndex}")
    public Map<ERest, Object> delete(@PathVariable String strIndex){
        return orderDto.orderDelete(strIndex);
    }

    //Sipariş teslim edilme durumu
    @PutMapping("/delivered/{stId}")
    public void productDelivered(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        obRepo.orderStatus(cid);

    }

    //Sipariş Hazırlanıyor edilme durumu
    @PutMapping("/loading/{stId}")
    public void productloading(@PathVariable String stId) {
        //jpa-----
        int cid = Integer.parseInt(stId);
        obRepo.deliveredStatus(cid);

    }


}
