package com.example.feign.demo;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.repository.PersonRepositoryHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class FeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);
        initializeData();
    }

    private static void initializeData() {
        PersonRepositoryHelper.getInstance().addPerson(new Person("Melek", "Tamtürk"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("İrem", "Üstünboyacıoğlu"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("Cemalettin", "Kaya"));
    }
}
