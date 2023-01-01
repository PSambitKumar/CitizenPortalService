package com.sambit.citizenportalservice.jwtConfig;

import com.sambit.citizenportalservice.serviceImpl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 31/12/2022 - 4:38 PM
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTConfiguration jwtConfiguration;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String token = null, userName = null, apiUserName = null, apiToken, authToken;

        authToken = httpServletRequest.getHeader("Authorization");
        apiToken = httpServletRequest.getHeader("Token");

        if (authToken != null && authToken.startsWith("Bearer ")) {
            token = authToken.substring(7);
            userName = jwtConfiguration.extractUsername(token);
        }

        if (apiToken != null) {
            apiUserName = jwtConfiguration.extractUsername(apiToken);
        }

        if (userName != null && apiUserName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            if (jwtConfiguration.validateApiToken(apiToken, apiUserName)){
                if (jwtConfiguration.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
