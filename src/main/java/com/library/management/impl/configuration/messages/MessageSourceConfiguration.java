package com.library.management.impl.configuration.messages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
/**
 * Loads messages for the application (supports i18N and dynamic reloading)
 *
 * Created by michael.clark on 1/22/15.
 */
@Configuration
public class MessageSourceConfiguration {

    @Value("${default.message.location}")
    private String defaultMessageLocation;

    @Value("${message.source.prefix}")
    private String messageSourceLocation;

    @Value("${message.source.filenames}")
    private String fileNames;

    //@Value("${messages.cache.seconds}")
    //private Integer cacheSeconds;

    @Bean
    public ReloadableResourceBundleMessageSource createMessageSource(){

        //Read from classpath if file path not specified.
        if( messageSourceLocation==null || messageSourceLocation.equals("")){
            messageSourceLocation = defaultMessageLocation;
        }

        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasenames(parseFileNames());
        ms.clearCache();
        //ms.setCacheSeconds(cacheSeconds);

        return ms;
    }

    private String[] parseFileNames(){

        String[] filePaths = fileNames.split(",");
        List<String> baseNames = new ArrayList();
        for( String s:filePaths){
            System.out.println("Loc:"+messageSourceLocation+s);
            baseNames.add(messageSourceLocation+s);
        }

        return baseNames.toArray(new String[baseNames.size()]);
    }
} 
   