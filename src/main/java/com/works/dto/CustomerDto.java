package com.works.dto;

import com.works.entities.Content;
import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.util.ERest;
import com.works.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerDto {
    final CustomerRepository cRepo;
    final Util util;

    public CustomerDto(CustomerRepository cRepo, Util util) {
        this.cRepo = cRepo;
        this.util = util;
    }


    //Müşteri Ekleme
    public Map<ERest, Object> customerAdd(Customer customer, BindingResult bResult) {
        Map<ERest , Object> hm = new LinkedHashMap<>();
        if(!bResult.hasErrors()){
            try {
                Customer customerDb = cRepo.save(customer);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Ekleme başarılı");
                hm.put(ERest.result, customerDb);
            } catch (Exception e) {
                hm.put(ERest.status, false);
            }

        }else{
            hm.put(ERest.status,false);
            hm.put(ERest.errors, util.errors(bResult));
        }

        return hm;
    }

    //Müşteri listeleme
    public Map<ERest,Object> customerList(){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status,true);
        List<Customer> ls = cRepo.findAll();
        hm.put(ERest.result,ls);
        return hm;
    }

    //Müşteri Silme
    public Map<ERest, Object>customerDelete (String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        int cuid = Integer.parseInt(strIndex);
        try{
            if(cRepo.existsById(cuid)){
                cRepo.deleteById(cuid);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Silme başarılı");
                hm.put(ERest.result, cuid);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme başarısız. Girilen Id yanlış");
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
