package com.example.feign.demo.controller;

import com.example.feign.demo.converter.PersonDTOConverter;
import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.builder.PersonDTOBuilder;
import com.example.feign.demo.model.response.PersonDTO;
import com.example.feign.demo.model.request.PersonFilterRequest;
import com.example.feign.demo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;
    @Mock
    private PersonDTOConverter personDTOConverter;

    @Test
    public void it_should_filter_person_with_name() {
        //given
        PersonFilterRequest personFilterRequest = new PersonFilterRequest();
        personFilterRequest.setName("İrem");

        Person person = new Person("İrem", "Üstün");
        Set<Person> persons = Sets.newSet(person);
        when(personService.findByName("İrem")).thenReturn(persons);

        PersonDTO personDTO = PersonDTOBuilder.aPersonDTO().name("İrem").surname("Üstün").build();
        when(personDTOConverter.applySet(persons)).thenReturn(Sets.newSet(personDTO));

        //when
        Set<PersonDTO> actualPersons = personController.filter(personFilterRequest);

        //then
        verify(personService).findByName("İrem");
        verify(personDTOConverter).applySet(persons);
        assertThat(actualPersons).containsExactlyInAnyOrder(personDTO);
    }

    @Test
    public void it_should_get_person_with_name() {
        //given
        String name = "Melek";
        Person person = new Person("Melek","Tamtürk");
        Set<Person> persons = Sets.newSet(person);
        when(personService.findByName(name)).thenReturn(persons);

        PersonDTO personDTO = PersonDTOBuilder.aPersonDTO().build();
        when(personDTOConverter.applySet(persons)).thenReturn(Sets.newSet(personDTO));

        //when
        Set<PersonDTO> actualPersons = personController.getPersonByName(name);

        //then
        verify(personService).findByName("Melek");
        verify(personDTOConverter).applySet(persons);
        assertThat(actualPersons).containsExactlyInAnyOrder(personDTO);
    }
}