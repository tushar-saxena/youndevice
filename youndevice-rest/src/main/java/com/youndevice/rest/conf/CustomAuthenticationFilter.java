package com.youndevice.rest.conf;


import com.youndevice.domain.Role;
import com.youndevice.domain.User;
import com.youndevice.rest.service.TokenAuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class CustomAuthenticationFilter extends GenericFilterBean {

    public static Map<String, User> validUserData = new HashMap<String, User>();

    private TokenAuthenticationService tokenAuthenticationService;

    protected CustomAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%  CustomAuthenticationFilter  %%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%  doFilter  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        User user = null;
        String authToken = ((HttpServletRequest) req).getHeader("X-Auth-Token");
        if (authToken != null && !authToken.isEmpty()) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            user = tokenAuthenticationService.getUserDetails(authToken, validUserData);
            if (user != null) {
                for (Role role : user.getRoles()) {
                    authorities.add(role);
                }
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authToken, "", authorities);
                validUserData.put(authToken, user);
                SecurityContextHolder.getContext().setAuthentication(token);
                chain.doFilter(req, res);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) res;
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
            }
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
            HttpServletResponse httpServletResponse = (HttpServletResponse) res;
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization header needed");
            return;
        }
    }


}