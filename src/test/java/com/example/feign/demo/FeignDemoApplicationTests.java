package com.example.feign.demo;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.repository.PersonRepositoryHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignDemoApplicationTests {

    @Test
    public void contextLoads() {
        initializeData();
    }

    private void initializeData() {
        PersonRepositoryHelper.getInstance().addPerson(new Person("İrem", "Üstünboyacıoğlu"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("Melek", "Tamtürk"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("Cemalettin", "Kaya"));
    }

}
