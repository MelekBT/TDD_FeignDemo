package com.example.demo.converter;

import com.example.demo.model.Person;
import com.example.demo.model.PersonCatalog;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PersonCatalogConverter implements Function<Person, PersonCatalog> {
    @Override
    public PersonCatalog apply(Person person) {
        PersonCatalog personCatalog = new PersonCatalog(person.getName()+"*",person.getSurname()+"*");
        return personCatalog;
    }

    public Set<PersonCatalog> applySet(Set<Person> persons){
        return persons.stream()
                .map(this)
                .collect(Collectors.toSet());
    }
}
