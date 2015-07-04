package com.library.management.impl.framework.exception;


import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Translate message keys to their string representation.
 *
 * Uses a reloadable resource bundle which reads files from
 * a location on the file system or within the current project
 *
 * Configuration is externalized in application.properties file
 *
 * Supports i18N and properties files for multiple domains
 * (eg, Timekeeping, Scheduling)
 *
 * Created by michael.clark on 1/22/15.
 */
public class ExceptionMessageResolver {

/*
    @Value("${default.message.locale}")
    private String defaultMessageLocaleString;

    private Locale defaultMessageLocale;

    @PostConstruct
    private void createDefaultResolver(){
        defaultMessageLocale = new Locale(defaultMessageLocaleString);
    }

    *//**
     * Resolve message using default Locale
     * @param code
     * @return
     *//*
    public String resolve( String code ){
        return messageSource.getMessage(code, new Object[]{}, defaultMessageLocale);
    }

    *//**
     * Resolve message using the specified Locale
     * @param key
     * @param locale
     * @return
     *//*
    public String resolve( String key, Locale locale ){
        return messageSource.getMessage(key, new Object[]{}, locale);
    }

    *//**
     * Resolve a message using supplied placeholder replacements and default Locale
     * @param key
     * @param args
     * @return
     *//*
    public String resolveWithParams( String key, Object[] args ){
        return resolveWithParams(key, args, null);
    }

    *//**
     *
     * Resolve a message using supplied placeholder replacements
     *
     * @param key
     * @param args
     * @param locale
     * @return
     *//*
    public String resolveWithParams( String key, Object[] args, Locale locale ){
        Locale theLocale = locale==null?defaultMessageLocale:locale;
        return messageSource.getMessage(key, args, theLocale);

    }
*/
}
