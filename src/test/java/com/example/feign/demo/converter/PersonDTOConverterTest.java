package com.example.feign.demo.converter;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.model.response.PersonDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class PersonDTOConverterTest {
    @InjectMocks
    PersonDTOConverter personDTOConverter;

    @Test
    public void it_should_convert_person_to_person_dto() {
        //given
        Person person = new Person("Melek","Tamtürk");

        //when
        PersonDTO personDTO = personDTOConverter.apply(person);

        //then
        assertEquals(personDTO.getName(),"Melek");
        assertEquals(personDTO.getSurname(),"Tamtürk");
    }
}