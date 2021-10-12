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
                .authorizeRequests()
                .antMatchers("/dashboard_mvc/**").hasRole("MVC")
                .antMatchers("/category_mvc/**").hasRole("MVC")
                .antMatchers("/content_mvc/**").hasRole("MVC")
                .antMatchers("/address_mvc/**").hasRole("MVC")
                .antMatchers("/announcement_mvc/**").hasRole("MVC")
                .antMatchers("/category_rest/**").hasRole("REST")
                .antMatchers("/content_rest/**").hasRole("REST")
                .antMatchers("/register/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login").defaultSuccessUrl("/dashboard_mvc", true).permitAll()
                .permitAll();
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

/*

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class RestApiSecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Autowired
        private JwtAuthenticationTokenFilter jwtauthFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http
                    .csrf().disable()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/**").hasAnyRole("REST")
                    .and()
                    .addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class);

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }

    @Configuration
    @Order(2)
    public static class LoginFormSecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
        {
            auth.inMemoryAuthentication().withUser("mvc").password(passwordEncoder.encode("12345")).roles("MVC");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http
                    .csrf().disable()
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers("/dashboard_mvc/**").permitAll()
                    .antMatchers("/**").hasRole("MVC")
                    .and().formLogin();

            http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
        }
    }
}*/
