package com.example.feign.demo.service;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


@Service
public class PersonService {

    private static final String NOT_FOUND_EXCEPTION = "%s not found";

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Set<Person> findByName(String name) {
        Set<Person> persons = personRepository.findByName(name);
        if (CollectionUtils.isEmpty(persons)) {
            throw new RuntimeException(String.format(NOT_FOUND_EXCEPTION,name));
        }

        return persons;
    }
}
