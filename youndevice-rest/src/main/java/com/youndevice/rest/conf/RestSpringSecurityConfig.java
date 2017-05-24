package com.youndevice.rest.conf;

import com.youndevice.rest.service.CustomUserDetailsService;
import com.youndevice.rest.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//@Configuration
//@EnableWebSecurity
//@Order(1)
public class RestSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;


    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    public RestSpringSecurityConfig() {
        super(true);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/register");
        web.ignoring().antMatchers("/util/info");
        web.ignoring().antMatchers("/appliance/device/status");
        web.ignoring().antMatchers("/appliance/device/changeWebStatus");
        web.ignoring().antMatchers("/appliance/device/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%  RestSpringSecurityConfig  %%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%  configure  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");


        http.csrf().disable();
        http.exceptionHandling().accessDeniedHandler(restAccessDeniedHandler);

        http.authorizeRequests()

                //allow anonymous resource requests
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/resources*//**").permitAll()

                //allow anonymous POSTs to login
                .antMatchers(HttpMethod.POST, "/login").permitAll()

                //allow anonymous POSTs to register
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()


                //all other request need to be authenticated
                .anyRequest().hasRole("USER").and()

                // custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
                .addFilterBefore(new StatelessLoginFilter("/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

                // custom Token based authentication based on the header previously given to the client
                .addFilterBefore(new CustomAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }*/
}
