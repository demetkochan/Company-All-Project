package com.works.config;

import com.works.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
   final UserService userService;

    public SpringSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.encoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/gallery_mvc/**").hasRole("MVC")
                .antMatchers("/uploadImage_mvc/**").permitAll()
                .antMatchers("/dashboard_mvc/**").hasRole("MVC")
                .antMatchers("/category_mvc/**").hasRole("MVC")
                .antMatchers("/advertising_mvc/**").hasRole("MVC")
                .antMatchers("/customer_mvc/**").hasRole("MVC")
                .antMatchers("/delivered_mvc/**").hasRole("MVC")
                .antMatchers("/galleryDetail_mvc/**").hasRole("MVC")
                .antMatchers("/home_mvc/**").hasRole("MVC")
                .antMatchers("/like_mvc/**").hasRole("MVC")
                .antMatchers("/order_mvc/**").hasRole("MVC")
                .antMatchers("/product_mvc/**").hasRole("MVC")
                .antMatchers("/settings_mvc/**").hasRole("MVC")
                .antMatchers("/survey_mvc/**").hasRole("MVC")
                .antMatchers("/surveydetail_mvc/**").hasRole("MVC")
                .antMatchers("/content_mvc/**").hasRole("MVC")
                .antMatchers("/address_mvc/**").hasRole("MVC")
                .antMatchers("/announcement_mvc/**").hasRole("MVC")
                .antMatchers("/api/category_rest/**").hasRole("REST")
                .antMatchers("/api/advertising_rest/**").hasRole("REST")
                .antMatchers("/api/content_rest/**").hasRole("REST")
                .antMatchers("/api/announcement_rest/**").hasRole("REST")
                .antMatchers("/api/customer_rest/**").hasRole("REST")
                .antMatchers("/api/home_rest/**").hasRole("REST")
                .antMatchers("/api/like_rest/**").hasRole("REST")
                .antMatchers("/api/product_rest/**").hasRole("REST")
                .antMatchers("/api/survey_rest/**").hasRole("REST")
                .antMatchers("/images/**").permitAll()
                .antMatchers("/about/**").permitAll()
                .antMatchers("/contact/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login").defaultSuccessUrl("/dashboard_mvc", true).permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessHandler(userService)
                .permitAll()
                .and()
              .exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fontawesome-free-5.15.4-web/**","/sitecss/**")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");
    }


}


