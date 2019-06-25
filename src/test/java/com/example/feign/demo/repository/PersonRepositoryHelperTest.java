package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonRepositoryHelperTest {

    @Test
    public void it_should_get_person_by_name() {
        //given
        String name = "Melek";

        //when
        Optional<Person> personOptinal = PersonRepositoryHelper.getInstance().findByName(name);

        //then
        assertThat(personOptinal.isPresent()).isTrue();
        Person person = personOptinal.get();
        assertThat(person.getName()).isEqualTo("Melek");
        assertThat(person.getSurname()).isEqualTo("Tamtürk");
    }
}