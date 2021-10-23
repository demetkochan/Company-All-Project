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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(" ")
    public String customer(Model model){
        model.addAttribute("ls",cRepo.findAll());

        return "customer";
    }

    @RequestMapping(method = RequestMethod.POST)
    @PostMapping("/add")
    public String customerAdd(Customer cus,Model model, @RequestParam(defaultValue = "1") String roleIDSt){
        try{
            int roleID = Integer.parseInt(roleIDSt);
            cus.setEnabled(true);
            cus.setTokenExpired(true);
            cus.setStatus(true);

            Role role= rRepo.findById(roleID).get();
            List<Role> roles=new ArrayList<>();
            roles.add(role);
            cus.setRoles(roles);


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

    @GetMapping("/search/{data}")
    public List<CustomerDoc> search(@PathVariable  String data) {
        Page<CustomerDoc> searchPage = cdRepo.findByName(data, PageRequest.of(0, 10));
        List<CustomerDoc> customerList = searchPage.getContent();
        return customerList;
    }


}
