package com.works.controller;

import com.works.entities.User;
import com.works.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/userUpdate_mvc")
public class UserUpdateController {

    final UserRepository uRepo;

    public UserUpdateController(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    @GetMapping("")
    public String userUpdate(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Optional<User> userOptional = uRepo.findByEmailEqualsAllIgnoreCase(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            model.addAttribute("user",user);
        }else {
            String error = "User is not found!";

            System.err.println(error);
        }


        return "userUpdate";
    }


    User us = new User();
    @ResponseBody
    @PostMapping("/add")
    public User updateUser(@RequestBody User user  ){
        System.out.println(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> ou = uRepo.findByEmailEqualsAllIgnoreCase(currentPrincipalName);

        Optional<User> findusers = uRepo.findById(ou.get().getId());
        System.out.println(findusers.get().getFirstName());

        try{
            if(findusers.isPresent()){

                User us = findusers.get();
                us.setFirstName(user.getFirstName());
                us.setLastName(user.getLastName());
                us.setEmail(user.getEmail());
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getEmail());

                uRepo.saveAndFlush(us);
                us = new User();
                return us;

            }else{
                String error = "User yok";
                System.err.println(error);
            }

        }catch (Exception ex){

        }



        return us;



    }





}
