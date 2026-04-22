package com.ecommerce.UserService.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader=request.getHeader("Authorization");

    //1. check header
    if(authHeader==null || !authHeader.startsWith("Bearer ")){
        filterChain.doFilter(request,response);
        return;
    }

    //2. Extract token
    String token=authHeader.substring(7);

    //3. Extract username
    String username=jwtUtil.extractUsername(token);

    if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        if(jwtUtil.validateToken(token,userDetails.getUsername())){
            UsernamePasswordAuthenticationToken auth=
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
    filterChain.doFilter(request,response);
    }
}
