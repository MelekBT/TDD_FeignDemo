package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

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
        Set<Person> persons = personRepository.findByName(name);

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
        Set<Person> personSet = personRepository.findByName(name);

        //then
        assertThat(personSet).isEmpty();
    }

    @Test
    public void it_should_get_person_by_id(){
        //Given
        Long id = 3L;
        //when
        Optional<Person> personOptional = personRepository.findById(id);
        //then
        assertThat(personOptional.isPresent()).isTrue();
        assertThat(personOptional.get().getId()).isEqualTo(3L);
        assertThat(personOptional.get().getName()).isEqualTo("Cemalettin");
        assertThat(personOptional.get().getSurname()).isEqualTo("Kaya");

    }

}