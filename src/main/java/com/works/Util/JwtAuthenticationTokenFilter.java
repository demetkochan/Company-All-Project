package com.works.util;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        final String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer "))
        {
            String authToken = header.substring(7);

            try
            {
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                if (username != null)
                {
                    if (jwtTokenUtil.validateToken(authToken, username))
                    {
                        // here username should be validated with database and get authorities from database if valid
                        // Say just to hard code

                        List<GrantedAuthority> authList = new ArrayList<>();
                        authList.add(new SimpleGrantedAuthority("ROLE_REST"));

                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authList);
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                    else
                    {
                        System.out.println("Token has been expired");
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Unable to get JWT Token, possibly expired");
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}*/
