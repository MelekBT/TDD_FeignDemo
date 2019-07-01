package com.example.feign.demo.service;

import com.example.feign.demo.exceptions.DomainNotFoundException;
import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.request.PersonCreateRequest;
import com.example.feign.demo.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void it_should_get_person_from_repository_by_name() {
        //given
        String name = "Melek";

        Person person1 = new Person(name, "surname-1");
        Person person2 = new Person(name,"surname-2");
        when(personRepository.findByName(name)).thenReturn(Sets.newSet(person1, person2));

        //when
        Set<Person> actualPersons = personService.findByName(name);

        //then
        verify(personRepository).findByName(name);
        assertThat(actualPersons).containsExactlyInAnyOrder(person1, person2);
    }

    @Test
    public void it_should_throw_exception_when_person_does_not_exist_with_given_name() {
        //given
        String name = "Irem";

        when(personRepository.findByName(name)).thenReturn(new HashSet<>());

        //when
        Throwable throwable = catchThrowable(() -> personService.findByName(name));

        //then
        assertThat(throwable).isInstanceOf(DomainNotFoundException.class);
        DomainNotFoundException exception = (DomainNotFoundException) throwable;
        assertThat(exception.getMessage()).isEqualTo("Irem not found");
    }

    @Test
    public void it_should_return_person_by_id(){
        //given
        Long id=2L;

        Person person = new Person("Melek","Tamtürk");
        person.setId(2L);
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        //when
        Person actualPerson = personService.findById(id);

        //then
        verify(personRepository).findById(id);
        assertThat(actualPerson.getId()).isEqualTo(id);
        assertThat(actualPerson.getName()).isEqualTo("Melek");
        assertThat(actualPerson.getSurname()).isEqualTo("Tamtürk");

    }

    @Test
    public void it_should_throw_exception_when_id_does_not_exist(){
        //given
        Long id=-1L;

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> personService.findById(id));

        //then
        assertThat(throwable).isInstanceOf(DomainNotFoundException.class);
        DomainNotFoundException domainNotFoundException = (DomainNotFoundException) throwable;
        assertThat(domainNotFoundException.getMessage()).isEqualTo("This person with id "+ id+" not found");
    }

    @Test
    public void  it_should_create_person_with_personDTO(){
        //given
        PersonCreateRequest personCreateRequest = new PersonCreateRequest();
        personCreateRequest.setName("İrem");
        personCreateRequest.setSurname("Tamtürk");

        //when
        personService.createPerson(personCreateRequest);

        //then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).save(personArgumentCaptor.capture());
        Person capturedPerson = personArgumentCaptor.getValue();
        assertThat(capturedPerson.getName()).isEqualTo("İrem");
        assertThat(capturedPerson.getSurname()).isEqualTo("Tamtürk");
    }
}