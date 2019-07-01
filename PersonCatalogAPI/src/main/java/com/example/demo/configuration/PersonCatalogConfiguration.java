package com.example.demo.configuration;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonCatalogConfiguration {
    @Bean
    public OkHttpClient client(){
        return new OkHttpClient();
    }
}
