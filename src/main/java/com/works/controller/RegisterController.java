package com.works.controller;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.redisrepository.SectorSessionRepository;
import com.works.repositories.RoleRepository;
import com.works.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    final UserService uService;
    final RoleRepository rRepo;
    final SectorSessionRepository sRepo;
    public RegisterController(UserService uService, RoleRepository rRepo, SectorSessionRepository sRepo) {
        this.uService = uService;
        this.rRepo = rRepo;
        this.sRepo = sRepo;
    }
    @GetMapping("")
    public String register(Model model){
        model.addAttribute("ls",sRepo.findAll());
        return "register";
    }
    @RequestMapping(method = RequestMethod.POST)
    @PostMapping("/register")
    public String register(User us, @RequestParam(defaultValue = "2") String roleIDSt){
        try{
            int roleID = Integer.parseInt(roleIDSt);
            us.setEnabled(true);
            us.setTokenExpired(true);

            Role role= rRepo.findById(roleID).get();
            List<Role> roles=new ArrayList<>();
            roles.add(role);
            us.setRoles(roles);

            uService.register(us);
            return "redirect:/login";
        }catch (Exception e){
            System.out.println(e);
        }
        return "register";
    }
}

