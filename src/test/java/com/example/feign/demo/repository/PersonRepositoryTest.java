package com.example.feign.demo.repository;

import com.example.feign.demo.model.Person;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest(PersonRepositoryHelper.class)
public class PersonRepositoryTest {

    @InjectMocks
    private PersonRepository personRepository;

    @Mock
    private PersonRepositoryHelper personRepositoryHelper;

    @BeforeClass
    public static void createData() {
        PersonRepositoryHelper.getInstance().addPerson(new Person("Melek", "Tamtürk"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("İrem", "Üstünboyacıoğlu"));
        PersonRepositoryHelper.getInstance().addPerson(new Person("Cemalettin", "Kaya"));
    }

    @AfterClass
    public static void clearData() {
        PersonRepositoryHelper.getInstance().clear();
    }

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
    public void it_should_get_empty_set_when_name_does_not_exist() {
        //given
        String name = "Meleksu";

        //when
        Set<Person> personSet = personRepository.findByName(name);

        //then
        assertThat(personSet).isEmpty();
    }

    @Test
    public void it_should_get_person_by_id() {
        //given
        Long id = 3L;

        //when
        Optional<Person> personOptional = personRepository.findById(id);

        //then
        assertThat(personOptional.isPresent()).isTrue();
        assertThat(personOptional.get().getId()).isEqualTo(3L);
        assertThat(personOptional.get().getName()).isEqualTo("Cemalettin");
        assertThat(personOptional.get().getSurname()).isEqualTo("Kaya");
    }

    @Test
    public void it_should_return_null_when_id_does_not_exist(){
        //given
        Long id=-1L;

        //when
        Optional<Person> actualPerson = personRepository.findById(id);

        //then
        assertThat(actualPerson.isPresent()).isFalse();
    }

    @Test
    public void it_should_add_person_to_repository(){
        //given
        Person person = new Person("Cenk","Çiviçi");

        //when
        personRepository.save(person);

        //then
    }

}