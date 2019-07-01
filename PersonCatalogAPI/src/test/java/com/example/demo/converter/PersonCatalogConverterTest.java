package com.example.demo.converter;

import com.example.demo.model.Person;
import com.example.demo.model.PersonCatalog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonCatalogConverterTest {
    @InjectMocks
    private PersonCatalogConverter personCatalogConverter;

    @Test
    public void it_should_convert_Person_to_PersonCatalog(){
        //given
        Person person = new Person("Melek","Tamtürk");
        PersonCatalog personCatalog = new PersonCatalog("Melek*","Tamtürk*");

        //when
        PersonCatalog actualPersonCatalog = personCatalogConverter.apply(person);
        //then
        assertThat(actualPersonCatalog.getName()).isEqualTo(personCatalog.getName());
        assertThat(actualPersonCatalog.getSurname()).isEqualTo(personCatalog.getSurname());
    }

    @Test
    public void it_should_convert_Persons_to_PersonCatalogs(){
        //given
        Person person1 = new Person("Melek","T");
        Person person2 = new Person("Melek","T2");

        //when
        Set<PersonCatalog> actualPersonCatalogs = personCatalogConverter.applySet(Sets.newSet(person1,person2));

        //then
        assertThat(actualPersonCatalogs).hasSize(2);

        Iterator<PersonCatalog> iterator = actualPersonCatalogs.iterator();

        PersonCatalog personCatalog1 = iterator.next();
        assertThat(personCatalog1.getName()).isEqualTo("Melek*");
        assertThat(personCatalog1.getSurname()).isEqualTo("T2*");

        PersonCatalog personCatalog2 = iterator.next();
        assertThat(personCatalog2.getName()).isEqualTo("Melek*");
        assertThat(personCatalog2.getSurname()).isEqualTo("T*");

    }
}