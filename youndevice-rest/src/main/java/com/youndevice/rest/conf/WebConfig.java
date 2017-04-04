package com.youndevice.rest.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT", "DELETE","OPTIONS","HEAD")
                .allowedHeaders("Access-Control-Allow-Headers", "X-Requested-With", "content-type","X-AUTH-TOKEN")
                .exposedHeaders("Access-Control-Allow-Headers", "X-AUTH-TOKEN")
                .allowCredentials(false).maxAge(3600);
    }
}