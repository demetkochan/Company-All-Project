package com.works.dto;

import com.works.entities.Customer;
import com.works.entities.OrderBox;
import com.works.entities.Product;
import com.works.repositories.CustomerRepository;
import com.works.repositories.OrderBoxRepository;
import com.works.repositories.ProductRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderDto {
    final OrderBoxRepository oRepo;
    final Util util;
    final ProductRepository pRepo;
    final CustomerRepository cRepo;

    public OrderDto(OrderBoxRepository oRepo, Util util, ProductRepository pRepo, CustomerRepository cRepo) {
        this.oRepo = oRepo;
        this.util = util;
        this.pRepo = pRepo;
        this.cRepo = cRepo;
    }

    //Ürün Listesi
    public Map<String,Object> productList(){
        Map<String ,Object> hm=new LinkedHashMap<>();
        hm.put("status",true);
        List<Product> ls = pRepo.findAll();
        hm.put("result",ls);
        return hm;
    }
    //Müşteri Listesi
    public Map<String,Object> customerList(){
        Map<String ,Object> hm=new LinkedHashMap<>();
        hm.put("status",true);
        List<Customer> ls = cRepo.findAll();
        hm.put("result",ls);
        return hm;
    }

    //Sipariş için ödenecek tutar hesabı
    public Map<ERest,Object> total(){
        Map<ERest, Object> hm = new LinkedHashMap<>();

        return hm;
    }



    //Sipariş Ekleme
    public Map<ERest, Object> orderAdd(OrderBox orderBox, BindingResult bResult) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                OrderBox orderBox1 = oRepo.save(orderBox);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, orderBox1);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }

        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }
    //Sipariş listesi
    public Map<ERest,Object> orderList(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<OrderBox> ls = oRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Sipariş silme
    public Map<ERest, Object> orderDelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int cuid = Integer.parseInt(strIndex);
        try{
            if(oRepo.existsById(cuid)){
                oRepo.deleteById(cuid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Silme başarılı");
                hm.put(ERest.result, cuid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, cuid);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme gerçekleşmedi");
            hm.put(ERest.result, cuid);
        }
        return hm;
    }
}
