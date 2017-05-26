package com.youndevice.rest.security;

import com.youndevice.rest.filters.FirebaseFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(new FirebaseFilter(), BasicAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //TODO Remove below lines
        web.ignoring().antMatchers("/appliance/device/status");
        web.ignoring().antMatchers("/appliance/device/changeWebStatus");
        web.ignoring().antMatchers("/appliance/device/register");
        web.ignoring().antMatchers("/device/register");
    }
}
