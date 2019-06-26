package com.example.feign.demo.controller;

import com.example.feign.demo.converter.PersonDTOConverter;
import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.request.PersonCreateRequest;
import com.example.feign.demo.model.request.PersonFilterRequest;
import com.example.feign.demo.model.response.PersonDTO;
import com.example.feign.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
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
    public PersonDTO getPersonById(@PathVariable Long id) {
        Person person = personService.findById(id);
        return personDTOConverter.apply(person);
    }

    @GetMapping("/by-name")
    public Set<PersonDTO> getPersonByName(@RequestParam(value="name") String name){
        Set<Person> persons = personService.findByName(name);
        return  personDTOConverter.applySet(persons);
    }

    @PostMapping
    public void createPerson(@RequestBody @Valid PersonCreateRequest personCreateRequest){
        personService.createPerson(personCreateRequest);
    }

}