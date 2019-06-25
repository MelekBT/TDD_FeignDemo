package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class PersonRepository {

    public Set<Person> findByName(String name) {
        return PersonRepositoryHelper.getInstance().findByName(name);
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(PersonRepositoryHelper.getInstance().findById(id));
    }
}
