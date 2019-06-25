package com.example.feign.demo.converter;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.response.PersonDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Component
public class PersonDTOConverter implements Function<Person, PersonDTO> {

    @Override
    public PersonDTO apply(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        return personDTO;
    }
    public Set<PersonDTO> applySet(Set<Person> persons){
        Set<PersonDTO> personSet = new HashSet<>();
        for(Person person : persons){
            personSet.add(this.apply(person));
        }
        return personSet;
    }

}
