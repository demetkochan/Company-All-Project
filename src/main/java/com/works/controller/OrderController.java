package com.works.controller;

import com.works.entities.OrderBox;
import com.works.repositories.OrderBoxRepository;
import com.works.repositories.ProductJoinOrder;
import com.works.services.UtilServices;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order_mvc")
public class OrderController {
    final UtilServices uService;
    final OrderBoxRepository obRepo;
    private static final Logger log=Logger.getLogger(OrderController.class);

    public OrderController(UtilServices uService, OrderBoxRepository obRepo) {
        this.uService = uService;
        this.obRepo = obRepo;
    }

    @GetMapping("")
    public String order(Model model){
        model.addAttribute("customer",uService.customerList());
        model.addAttribute("product",uService.productList());
        return "order";
    }

    @ResponseBody
    @PostMapping("/add")
    public OrderBox add(@RequestBody OrderBox orderBox){
        //jpa-----
        OrderBox o = obRepo.save(orderBox);
        return o;
    }

    //Order Listeleme
    @ResponseBody
    @GetMapping("/list")
    public List<ProductJoinOrder> orderList(Model model){
        List<ProductJoinOrder> order = obRepo.order();
        model.addAttribute("total",order);
        return order;

    }


    //Order silme
    @ResponseBody
    @DeleteMapping(value = "/delete/{stid}")
    public String delete(@PathVariable String stid) {
        String status = "0";
        try{
            int pid = Integer.parseInt(stid);
            obRepo.deleteById(pid);
            status= "1";

        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.err.println("Silme sırasında hata oluştu");
        }

        return status;

    }

}