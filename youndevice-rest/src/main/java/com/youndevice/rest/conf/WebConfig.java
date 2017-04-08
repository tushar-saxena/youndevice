package com.youndevice.rest.conf;

import com.youndevice.conf.ConfigMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {ConfigMarker.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT", "DELETE","OPTIONS","HEAD")
                .allowedHeaders("Access-Control-Allow-Origin", "X-Requested-With", "content-type","X-AUTH-TOKEN")
                .exposedHeaders("X-AUTH-TOKEN")
                .allowCredentials(false).maxAge(3600);
    }
}
