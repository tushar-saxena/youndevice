package com.youndevice.admin.conf;

import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.youndevice.web")
public class AppConfig extends WebMvcConfigurerAdapter implements ApplicationListener<ContextRefreshedEvent> {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }


    @Override
    /*
    * This method comes from ApplicationListener which is bind to listen the ContextRefreshedEvent and call this
    * method whenever the  context is started (or refreshed).
    */
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }

    // optional
    // only required because we are using @Email and @NotBlank annotations (JSR-303) for validation
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

}
