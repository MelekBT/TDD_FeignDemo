package com.example.feign.demo;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.repository.PersonRepositoryHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);
        initializeData();
    }

  /*  @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }*/

    private static void initializeData() {
        PersonRepositoryHelper.getInstance().addPerson(new Person("Melek", "Tamtürk"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("İrem", "Üstünboyacıoğlu"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("Cemalettin", "Kaya"));
    }
}
