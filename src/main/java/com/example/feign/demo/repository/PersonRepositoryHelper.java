package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class PersonRepositoryHelper {

    private AtomicLong personObjectIndex = new AtomicLong(0L);
    private List<Person> persons = new ArrayList<>();

    private static PersonRepositoryHelper instance;

    private PersonRepositoryHelper() {

    }

    public void addPerson(Person person) {
        person.setId(personObjectIndex.incrementAndGet());
        this.persons.add(person);
    }

    public void clear() {
        this.persons.clear();
    }


    public static synchronized PersonRepositoryHelper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PersonRepositoryHelper();
        }

        return instance;
    }

    public Set<Person> findByName(String name) {
        return persons.stream()
                .filter(person -> person.getName().equals(name))
                .collect(Collectors.toSet());
    }

    public Person findById(Long id) {
        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
