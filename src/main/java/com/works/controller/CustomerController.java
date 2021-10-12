package com.works.controller;

import com.works.entities.Customer;
import com.works.entities.Product;
import com.works.entities.Role;
import com.works.repositories.CustomerRepository;
import com.works.repositories.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer_mvc")
public class CustomerController {
    final RoleRepository rRepo;
    final CustomerRepository cRepo;
    public CustomerController(RoleRepository rRepo, CustomerRepository cRepo) {
        this.rRepo = rRepo;
        this.cRepo = cRepo;
    }

    @GetMapping(" ")
    public String customer(Model model){
        model.addAttribute("ls",cRepo.findAll());
        return "customer";
    }

    @RequestMapping(method = RequestMethod.POST)
    @PostMapping("/add")
    public String customerAdd(Customer cus, @RequestParam(defaultValue = "1") String roleIDSt){
        try{
            int roleID = Integer.parseInt(roleIDSt);
            cus.setEnabled(true);
            cus.setTokenExpired(true);

            Role role= rRepo.findById(roleID).get();
            List<Role> roles=new ArrayList<>();
            roles.add(role);
            cus.setRoles(roles);
            cRepo.save(cus);
            return "customer";

        }catch (Exception e){
            System.out.println(e);
        }
        return "customer";
    }



}
