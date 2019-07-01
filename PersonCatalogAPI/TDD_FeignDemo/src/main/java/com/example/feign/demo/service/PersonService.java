package com.example.feign.demo.service;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private static final String NOT_FOUND_EXCEPTION = "%s not found";

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findByName(String name) {
        return personRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format(NOT_FOUND_EXCEPTION, name)));
        /*
        Optional<Person> personOptional = personRepository.findByName(name);
        if (personOptional.isPresent()) {
            return personOptional.get();
        }

        throw new RuntimeException(String.format(NOT_FOUND_EXCEPTION, name));

         */

    }
}
