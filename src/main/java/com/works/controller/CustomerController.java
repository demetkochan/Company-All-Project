package com.works.controller;

import com.works.entities.Customer;
import com.works.entities.Role;
import com.works.models.CustomerDoc;
import com.works.repositories.CustomerDocRepository;
import com.works.repositories.CustomerRepository;
import com.works.repositories.RoleRepository;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/customer_mvc")
public class CustomerController {
    private static final Logger log=Logger.getLogger(CustomerController.class);
    final RoleRepository rRepo;
    final CustomerRepository cRepo;
    final CustomerDocRepository cdRepo;
    public CustomerController(RoleRepository rRepo, CustomerRepository cRepo, CustomerDocRepository cdRepo) {
        this.rRepo = rRepo;
        this.cRepo = cRepo;
        this.cdRepo = cdRepo;
    }
    Integer searchSize;
    @GetMapping(" ")
    public String customer(Model model){

        return "customer";
    }

    @RequestMapping(method = RequestMethod.POST)
    @PostMapping("/add")
    public String customerAdd(Customer cus,Model model){
        try{

            cus.setEnabled(true);
            cus.setTokenExpired(true);
            cus.setStatus(true);



            cRepo.save(cus);
            model.addAttribute("ls",cRepo.findAll());

            List<Customer> cl=cRepo.findAll();
            CustomerDoc cd=new CustomerDoc();


            cdRepo.deleteAll();

            cl.forEach(item->{
                cd.setEnabled(true);
                cd.setTokenExpired(true);
                cd.setStatus(true);
                cd.setCemail(item.getCemail());
                cd.setCname(item.getCname());
                cd.setCsurname(item.getCsurname());
                cd.setId(item.getId().toString());
                cd.setCphone(item.getCphone());
                cdRepo.save(cd);
            });

            return "customer";

        }catch (Exception e){
            log.error("Müşteri ekleme hatası");
            System.out.println(e);
        }
        return "customer";
    }
    Customer customerUpdate=new Customer();

    @GetMapping("/delete/{stid}")
    public String customerDelete(@PathVariable String stid){
        try{
            int id=Integer.parseInt(stid);
            cRepo.deleteById(id);
            customerUpdate=new Customer();
        }catch (Exception e){
            log.error("Silme hatası oluştu.");
            System.out.println("Silme sırasında hata oluştu.");
        }

        return "redirect:/customer_mvc";
    }

    @ResponseBody
    @GetMapping("/customerList/{pageNo}/{stpageSize}")
    public List<Customer> customerList(@PathVariable String pageNo, @PathVariable String stpageSize ){
        int ipageNumber = Integer.parseInt(pageNo);
        int pageSize = Integer.parseInt(stpageSize);

        if (pageSize == -1){
            List<Customer> lsx = new ArrayList<>();
            Iterable<Customer> page = cRepo.findAll();
            List<Customer> crList = cRepo.findAll();
            cdRepo.deleteAll();
            for(Customer item : page){
                lsx.add(item);
            }
            crList.forEach(item ->{
                CustomerDoc customerDoc = new CustomerDoc();
                customerDoc.setId(item.getId().toString());
                customerDoc.setCname(item.getCname());
                customerDoc.setCsurname(item.getCsurname());
                customerDoc.setCemail(item.getCemail());
                customerDoc.setCphone(item.getCphone());


                cdRepo.save(customerDoc);

            });
            return lsx;

        }else {
            Pageable pageable = PageRequest.of(ipageNumber,pageSize);
            Slice<Customer> pageList = cRepo.findByOrderByIdAsc(pageable);
            List<Customer> ls = pageList.getContent();
            List<Customer> customerList = cRepo.findAll();
            cdRepo.deleteAll();
            for(Customer item : customerList ) {
                CustomerDoc customerDoc = new CustomerDoc();
                customerDoc.setId(item.getId().toString());
                customerDoc.setCname(item.getCname());
                customerDoc.setCsurname(item.getCsurname());
                customerDoc.setCemail(item.getCemail());
                customerDoc.setCphone(item.getCphone());


                cdRepo.save(customerDoc);
            }
            return  ls;
        }


    }
    @ResponseBody
    @GetMapping("/search/{pageNo}/{stpageSize}/{data}")
    public List<CustomerDoc> contentSearch(@PathVariable String data, @PathVariable int pageNo, @PathVariable int stpageSize ){

        Page<CustomerDoc> pages = cdRepo.findByName(data, PageRequest.of(pageNo,stpageSize));
        List<CustomerDoc> list = pages.getContent();
        List<CustomerDoc> listc =  cdRepo.find(data);
        searchSize = listc.size();
        return  list;

    }

    @ResponseBody
    @GetMapping("/customerList/pageCount/{stpageSize}/{stPageStatus}")
    public Integer pageCount(@PathVariable String stpageSize,@PathVariable String stPageStatus){
        Integer pageStatus = Integer.parseInt(stPageStatus);
        long dataCount;
        if(pageStatus == 1) {
            dataCount = cRepo.count();
        }
        else {
            dataCount = searchSize;
        }
        double totalPageCount = Math.ceil((double)dataCount/Double.parseDouble(stpageSize));
        int pageCount = (int) totalPageCount;
        System.out.println("PageCount : " + pageCount);
        return pageCount;

    }



}
