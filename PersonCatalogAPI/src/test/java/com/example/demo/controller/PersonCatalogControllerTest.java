package com.example.demo.controller;

import com.example.demo.client.PersonApiClient;
import com.example.demo.converter.PersonCatalogConverter;
import com.example.demo.model.Person;
import com.example.demo.model.PersonCatalog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonCatalogControllerTest {

    @InjectMocks
    private PersonCatalogController personCatalogController;

    @Mock
    private PersonApiClient personApiClient;

    @Mock
    private PersonCatalogConverter personCatalogConverter;

    @Test
    public void it_should_get_person_catalog_by_id() {
        //given
        Person person = new Person();
        when(personApiClient.getPersonById(10L)).thenReturn(person);

        PersonCatalog personCatalog = new PersonCatalog("Melek", "Tamtürk");
        when(personCatalogConverter.apply(person)).thenReturn(personCatalog);

        //when
        PersonCatalog actualPersonCatalog = personCatalogController.getPersonCatalogById(10L);

        //then
        verify(personApiClient).getPersonById(10L);
        verify(personCatalogConverter).apply(person);

        assertThat(actualPersonCatalog).isEqualTo(personCatalog);
        assertThat(actualPersonCatalog).isEqualToComparingFieldByField(personCatalog);

        verifyNoMoreInteractions(personApiClient, personCatalogConverter);
    }

    @Test
    public void it_should_return_personCatalogs_by_name() {
        //given
        String name = "Melek";

        Set<Person> persons = Sets.newSet(new Person("Melek", "T"));
        when(personApiClient.getPersonByName(name)).thenReturn(persons);

        PersonCatalog personCatalog = new PersonCatalog("Melek*", "T*");
        when(personCatalogConverter.applySet(persons)).thenReturn(Sets.newSet(personCatalog));

        //when
        Set<PersonCatalog> actualPersonCatalogs = personCatalogController.getPersonCatalogByName(name);

        //then
        verify(personApiClient).getPersonByName(name);
        verify(personCatalogConverter).applySet(persons);

        assertThat(actualPersonCatalogs).hasSize(1);
        Iterator<PersonCatalog> iterator = actualPersonCatalogs.iterator();
        PersonCatalog actualPersonCatalog = iterator.next();
        assertThat(actualPersonCatalog.getName()).isEqualTo("Melek*");
        assertThat(actualPersonCatalog.getSurname()).isEqualTo("T*");

        verifyNoMoreInteractions(personApiClient, personCatalogConverter);
    }

    @Test
    public void it_should_throw_exception_when_any_exception_occurred() {
        //given
        when(personApiClient.getPersonById(10L)).thenThrow(new RuntimeException("exception"));

        //when
        Throwable throwable = catchThrowable(() -> personCatalogController.getPersonCatalogById(10L));

        //then
        verify(personApiClient).getPersonById(10L);

        assertThat(throwable).isInstanceOf(RuntimeException.class);
        RuntimeException exception = (RuntimeException) throwable;
        assertThat(exception.getMessage()).isEqualTo("exception");

        verifyNoMoreInteractions(personApiClient);
        verifyZeroInteractions(personCatalogConverter);
    }

    @Test
    public void it_should_return_empty_set_when_no_person_has_been_found() {
        //given
        String personName = "name";
        when(personApiClient.getPersonByName(personName)).thenThrow(new RuntimeException("exception"));

        //when
        Set<PersonCatalog> personCatalogs = personCatalogController.getPersonCatalogByName(personName);

        //then
        verify(personApiClient).getPersonByName(personName);

        assertThat(personCatalogs).isEmpty();

        verifyNoMoreInteractions(personApiClient);
        verifyZeroInteractions(personCatalogConverter);
    }

}