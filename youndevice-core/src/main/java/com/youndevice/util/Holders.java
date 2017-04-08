package com.youndevice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Holders {

    private static ApplicationContext applicationContext;
    private static MessageSource messageSource;

    @Autowired
    public Holders(ApplicationContext applicationContext,
                   MessageSource messageSource) {

        Holders.applicationContext = applicationContext;
        Holders.messageSource = messageSource;
    }


    /**
     * Gets the reference to an application-context bean
     *
     * @param clazz the type of the bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }


    /**
     * Gets a message from messages.properties
     *
     * @param messageKey the key of the message
     * @param args       any arguments
     */
    public static String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args,
                LocaleContextHolder.getLocale());
    }
}
