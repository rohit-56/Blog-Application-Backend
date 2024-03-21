package com.example.BloggerApp.configurations;

import com.example.BloggerApp.service.impl.CustomUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        if(requestToken == null){
            filterChain.doFilter(request,response);
            return;
        }

        String username = null;
        String token = null;
        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try{
               username = jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                System.out.println("Unable to get Jwt Token");
            }catch (MalformedJwtException e){
                System.out.println("Invalid token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt token is expired");
            }
        }
        else{
            System.out.println("JWT token does not start with Bearer");
            throw new AccessDeniedException("JWT token does not start with Bearer");
        }

        // once we get the token now validate it
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

            if(this.jwtTokenHelper.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                System.out.println("Invalid Token");
            }
        }
        else {
            System.out.println("Username is null or context is not null");
            throw new InsufficientAuthenticationException("Username is null or context is not null");
        }
        filterChain.doFilter(request,response);
    }
}
