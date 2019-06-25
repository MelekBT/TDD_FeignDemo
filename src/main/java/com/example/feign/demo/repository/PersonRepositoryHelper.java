package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PersonRepositoryHelper {

    private Map<String, Person> persons = new HashMap();

    private static PersonRepositoryHelper instance;

    private PersonRepositoryHelper() {
        persons.put("İrem", new Person("İrem", "Üstünboyacıoğlu"));
        persons.put("Melek", new Person("Melek", "Tamtürk"));
        persons.put("Cemalettin", new Person("Cemalettin", "Kaya"));
    }

    public static synchronized PersonRepositoryHelper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PersonRepositoryHelper();
        }

        return instance;
    }

    public Optional findByName(String name) {
        return Optional.ofNullable(persons.get(name));
    }
}
