package com.akash.udemy.microservices.restfulwebservices.model.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GreetingResource {

    @Autowired
    private ResourceBundleMessageSource messageSource;

//    Normal Greet
    @GetMapping("/greet")
    public String greet(){
        return "Good Morning";
    }

//    Internationalized greet -
    /*
    * Configuration ->
        -LocaleResolver-bean
            -Default Locale - Locale.US
        -ResourceBundleMessageSource

    * Usage ->
        -Autowire MessageSource
        -@RequestHeader(value="Accept-Language", required=false) Locale locale
        -messageSource.getMessage("goodmorning.message",null,locale)
    * */

    @GetMapping("/greet-internationalised")
    public String greetByLocale(@RequestHeader(name="Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("good.morning.message",null,locale);
    }
}
