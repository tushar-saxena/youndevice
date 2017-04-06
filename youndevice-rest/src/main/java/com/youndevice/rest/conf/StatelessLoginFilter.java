package com.youndevice.rest.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youndevice.domain.User;
import com.youndevice.rest.dto.UserAuthentication;
import com.youndevice.rest.service.CustomUserDetailsService;
import com.youndevice.rest.service.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final CustomUserDetailsService userDetailsService;

    public StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
                                CustomUserDetailsService userDetailsService, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(urlMapping));
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {


        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%  StatelessLoginFilter  %%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%  attemptAuthentication  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        User user = new User();
        StringBuffer requestData = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                requestData.append(line);
        } catch (IOException e) {
            e.printStackTrace();
            user = new User();
        }
        String requestDataString = requestData.toString();
        if (requestDataString != null && !requestDataString.isEmpty() ) {
            user = new ObjectMapper().readValue(requestDataString, User.class);
        }


        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                user.getEmailId(), user.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {


        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%  StatelessLoginFilter  %%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%  successfulAuthentication  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        // Lookup the complete User object from the database and create an Authentication for it
        final UserDetails authenticatedUser = userDetailsService.loadUserByUsername(authentication.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(new User(authenticatedUser.getUsername(), authenticatedUser.getPassword()));

        // Add the custom token as HTTP header to the response
        tokenAuthenticationService.addAuthentication(response, userAuthentication);
        response.addHeader("Access-Control-Allow-Origin", "*");
        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
