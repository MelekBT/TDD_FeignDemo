package com.example.feign.demo.converter;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.response.PersonDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonDTOConverterTest {

    @InjectMocks
    PersonDTOConverter personDTOConverter;

   // @Test
  /*  public void it_should_convert_person_to_person_dto() {
        //given
        Person person = new Person("Melek","Tamtürk");

        //when
        PersonDTO personDTO = personDTOConverter.apply(person);

        //then
        assertThat(personDTO.getName()).isEqualTo("Melek");
        assertThat(personDTO.getSurname()).isEqualTo("Tamtürk");
    }

    @Test
    public void it_should_convert_persons_to_personsDTO(){
        //given
        Person person1 = new Person("name1","surname1");
        Person person2 = new Person("name2","surname2");

        //when
        Set<PersonDTO> personDTOs = personDTOConverter.applySet(Sets.newSet(person1, person2));

        //then
        Iterator<PersonDTO> iterator = personDTOs.iterator();

        PersonDTO personDTO1 = iterator.next();
        assertThat(personDTO1.getName()).isEqualTo("name1");
        assertThat(personDTO1.getSurname()).isEqualTo("surname1");

        PersonDTO personDTO2 = iterator.next();
        assertThat(personDTO2.getName()).isEqualTo("name2");
        assertThat(personDTO2.getSurname()).isEqualTo("surname2");
    }*/
}