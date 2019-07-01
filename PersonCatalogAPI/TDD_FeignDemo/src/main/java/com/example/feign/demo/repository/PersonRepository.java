package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepository {

    public Optional<Person> findByName(String name) {
        return PersonRepositoryHelper.getInstance().findByName(name);
    }
}
