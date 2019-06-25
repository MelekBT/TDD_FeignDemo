package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonRepositoryHelperTest {

    @AfterEach
    public void tearDown() {
        PersonRepositoryHelper.getInstance().clear();
    }

    @Test
    public void it_should_get_person_by_id(){
        //given
        Long id = 3L;

        PersonRepositoryHelper.getInstance().addPerson(new Person("İrem", "Üstünboyacıoğlu"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("Melek", "Tamtürk"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("Cemalettin", "Kaya"));

        //when
        Person person = PersonRepositoryHelper.getInstance().findById(id);

        //then
        assertThat(person.getId()).isEqualTo(3L);
        assertThat(person.getName()).isEqualTo("Cemalettin");
        assertThat(person.getSurname()).isEqualTo("Kaya");
    }

    @Test
    public void it_should_get_person_set_by_name() {
        //given
        String name = "Melek";

        PersonRepositoryHelper.getInstance().addPerson(new Person("Melek", "Tamtürk"));

        //when
        Set<Person> persons = PersonRepositoryHelper.getInstance().findByName(name);

        //then
        assertThat(persons).hasSize(1);
        Person person = persons.iterator().next();
        assertThat(person.getName()).isEqualTo("Melek");
        assertThat(person.getSurname()).isEqualTo("Tamtürk");
    }

    @Test
    public void it_should_get_empty_set_for_nonexisting_name() {
        //given
        String name = "Meleksu";

        //when
        Set<Person> personSet = PersonRepositoryHelper.getInstance().findByName(name);

        //then
        assertThat(personSet).isEmpty();
    }

    @Test
    public void it_should_return_null_when_id_does_not_exist(){
        //given
        Long id = 999L;

        //when
        Person person = PersonRepositoryHelper.getInstance().findById(id);

        //then
        assertThat(person).isNull();
    }
}