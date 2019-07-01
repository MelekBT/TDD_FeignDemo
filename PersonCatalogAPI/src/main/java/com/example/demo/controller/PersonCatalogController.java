package com.example.demo.controller;

import com.example.demo.client.PersonApiClient;
import com.example.demo.converter.PersonCatalogConverter;
import com.example.demo.model.Person;
import com.example.demo.model.PersonCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/person-catalog")
public class PersonCatalogController {

    private final PersonCatalogConverter personCatalogConverter;
    private final PersonApiClient personApiClient;

    @Autowired
    public PersonCatalogController(PersonCatalogConverter personCatalogConverter,
                                   PersonApiClient personApiClient) {
        this.personCatalogConverter = personCatalogConverter;
        this.personApiClient = personApiClient;
    }

    @RequestMapping("/{id}")
    public PersonCatalog getPersonCatalogById(@PathVariable("id") Long id) {
        Person person = personApiClient.getPersonById(id);
        return personCatalogConverter.apply(person);

    }

    @RequestMapping("/name")
    public Set<PersonCatalog> getPersonCatalogByName(@RequestParam("name") String name) {
        Set<PersonCatalog> personCatalogs = new HashSet<>();
        try {
            Set<Person> persons = personApiClient.getPersonByName(name);
            personCatalogs = personCatalogConverter.applySet(persons);
        } catch (Exception e) {
            //No person has been found
        }
        return personCatalogs;
    }

}
