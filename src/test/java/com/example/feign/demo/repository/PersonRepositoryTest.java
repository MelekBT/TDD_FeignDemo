package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepositoryTest {

    @InjectMocks
    private PersonRepository personRepository;

    @Test
    public void it_should_get_person_by_name() {
        //given
        String name = "Melek";

        //when
        Optional<Person> personOptional = personRepository.findByName(name);

        //then
        assertThat(personOptional.isPresent()).isTrue();
        Person person = personOptional.get();
        assertThat(person.getName()).isEqualTo("Melek");
        assertThat(person.getSurname()).isEqualTo("Tamtürk");
    }

}