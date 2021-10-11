package com.works.services;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends SimpleUrlLogoutSuccessHandler implements UserDetailsService, LogoutSuccessHandler {
    final UserRepository uRepo;

    public UserService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public UserDetails loadUserByUsername(String email){
        UserDetails userDetails=null;
        Optional<User> oUser= uRepo.findByEmailEqualsAllIgnoreCase(email);
        if(oUser.isPresent()){
            System.out.println("Girildi");
            User us=oUser.get();
            userDetails =new org.springframework.security.core.userdetails.User(
                    us.getEmail(),
                    us.getPassword(),
                    us.isEnabled(),
                    us.isTokenExpired(),
                    true,
                    true,
                    getAuthorities(us.getRoles()));
        }else{
            throw new UsernameNotFoundException("Kullanıcı adı ya da şifre hatalı");
        }
        return userDetails;
    }
    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        }
        return authorities;
    }

    public User register(User us) throws AuthenticationServiceException {
        if(!com.works.Util.Util.isEmail(us.getEmail())){
            throw new AuthenticationServiceException("Bu mail formatı hatalıdır!");
        }

        Optional<User> uOpt= uRepo.findByEmailEqualsAllIgnoreCase(us.getEmail());
        if(uOpt.isPresent()){
            throw new AuthenticationServiceException("Bu kullanıcı daha önce kayıt edilmiştir.");
        }

        us.setPassword(encoder().encode(us.getPassword()));


        return uRepo.save(us);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("onLogoutSuccess Call ");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
    }


}
