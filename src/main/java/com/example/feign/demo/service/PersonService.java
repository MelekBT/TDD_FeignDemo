package com.example.feign.demo.service;

import com.example.feign.demo.exceptions.DomainNotFoundException;
import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.request.PersonCreateRequest;
import com.example.feign.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;


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
            throw new DomainNotFoundException(String.format(NOT_FOUND_EXCEPTION,name));
        }

        return persons;
    }

    public Person findById(Long id){
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(!optionalPerson.isPresent()) {
            throw new DomainNotFoundException(String.format(NOT_FOUND_EXCEPTION, "This person with id " + id));
        }
        return optionalPerson.get();
    }

    public void createPerson(PersonCreateRequest personCreateRequest){
        Person person = new Person(personCreateRequest.getName(), personCreateRequest.getSurname());
        personRepository.save(person);
    }
}
