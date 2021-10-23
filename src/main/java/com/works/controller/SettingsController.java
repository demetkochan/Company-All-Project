package com.works.controller;

import com.works.entities.ChangePassword;
import com.works.entities.User;
import com.works.repositories.UserRepository;
import com.works.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.Optional;

@Controller
@RequestMapping("/settings_mvc")
public class SettingsController {

    final UserRepository uRepo;
    final UserService userService;

    public SettingsController(UserRepository uRepo, UserService userService) {
        this.uRepo = uRepo;
        this.userService = userService;
    }


    @GetMapping("")
    public String settings(Model model){
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

        return "settings";
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


        try{
            if(findusers.isPresent()){

                User us = findusers.get();
                us.setCompanyName(user.getCompanyName());
                us.setCompanyAddress(user.getCompanyAddress());
                us.setPhone(user.getPhone());
                System.out.println(user.getCompanyName());
                System.out.println(user.getCompanyAddress());
                System.out.println(user.getPhone());

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

    //Şifre Değiştirme
    User us2 = new User();
    @ResponseBody
    @PostMapping("/changePassword")
    public User updatePassword(@RequestBody ChangePassword changePassword  ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> ou = uRepo.findByEmailEqualsAllIgnoreCase(currentPrincipalName);

        Optional<User> findusers = uRepo.findById(ou.get().getId());

        try{
            if(findusers.isPresent()){

                if(changePassword.getNewPassword().equals("")||changePassword.getRenewPassword().equals("")){
                    System.err.println("Boşlukları doldurun");
                    JOptionPane.showConfirmDialog(null,"Tüm Boşlukları Doldurun");

                }
                else {
                    if(changePassword.getNewPassword().equals(changePassword.getRenewPassword())){

                        User us2 = findusers.get();
                        us2.setPassword(userService.encoder().encode(changePassword.getNewPassword()));


                        uRepo.saveAndFlush(us2);
                        us2 = new User();
                        return us2;
                    }else{

                        System.err.println("Şifreniz aynı değil");
                        JOptionPane.showConfirmDialog(null,"Şifreniz aynı değil");
                    }

                }


            }else{
                String error = "User yok";
                JOptionPane.showConfirmDialog(null,"Böyle bir kullanıcı yok");
                System.err.println(error);
            }

        }catch (Exception ex){

        }

        return us2;

    }









}