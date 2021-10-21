package com.works.elasticsearchdto;

import com.works.models.CustomerDoc;
import com.works.repositories.CustomerDocRepository;
import com.works.util.ERest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerDocDto {
    final CustomerDocRepository cRepo;

    public CustomerDocDto(CustomerDocRepository cRepo) {
        this.cRepo = cRepo;
    }

    public Map<ERest, Object> add(CustomerDoc cdoc){
        Map<ERest,Object> hm=new LinkedHashMap<>();
        CustomerDoc customerDoc = cRepo.save(cdoc);
        hm.put(ERest.status, true);
        hm.put(ERest.message, "Ekleme Başarılı");
        hm.put(ERest.result, customerDoc);
        return hm;
    }


    public Map<ERest, Object> list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Iterable<CustomerDoc> iterableLs = cRepo.findAll();
        List<CustomerDoc> customerDocList = new ArrayList<>();
        iterableLs.forEach(customerDocList::add);
        hm.put(ERest.result,customerDocList);
        return hm;
    }


    public Map<ERest, Object> delete(String strIndex){
        Map<ERest, Object> hm = new HashMap<>();
        try{
            if(cRepo.existsById(strIndex)){
                cRepo.deleteById(strIndex);
                hm.put(ERest.status, true);
                hm.put(ERest.message, "Silme başarılı");
                hm.put(ERest.result, strIndex);
            }else{
                hm.put(ERest.status, false);
                hm.put(ERest.message, "Silme Başarısız. Girilen Id yanlış");
                hm.put(ERest.result, strIndex);
            }
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Silme gerçekleşmedi");
            hm.put(ERest.result, strIndex);
        }
        return hm;
    }


    public Map<ERest, Object> search(String data) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        Page<CustomerDoc> searchPage = cRepo.findByName(data, PageRequest.of(0, 10));
        List<CustomerDoc> customerList = searchPage.getContent();
        hm.put(ERest.status, true);
        hm.put(ERest.result,customerList);
        return hm;
    }
}
