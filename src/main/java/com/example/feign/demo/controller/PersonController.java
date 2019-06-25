package com.example.feign.demo.controller;

import com.example.feign.demo.converter.PersonDTOConverter;
import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.response.PersonDTO;
import com.example.feign.demo.model.request.PersonFilterRequest;
import com.example.feign.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController("/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonDTOConverter personDTOConverter;

    public PersonController(PersonService personService, PersonDTOConverter personDTOConverter) {
        this.personService = personService;
        this.personDTOConverter = personDTOConverter;
    }

    @GetMapping
    public Set<PersonDTO> filter(@ModelAttribute PersonFilterRequest filterRequest){
        String name = filterRequest.getName();
        Set<Person> persons = personService.findByName(name);

        return personDTOConverter.applySet(persons);
    }

    @GetMapping("/{id}")
    PersonDTO getPersonById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/by-name")
    public Set<PersonDTO> getPersonByName(@RequestParam("name") String name){
        Set<Person> persons = personService.findByName(name);
        return  personDTOConverter.applySet(persons);
    }

}