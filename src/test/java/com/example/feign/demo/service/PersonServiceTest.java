package com.example.feign.demo.service;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.repository.PersonRepository;
import com.example.feign.demo.repository.PersonRepositoryHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

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

        Person melek = new Person("Melek", "Tamtürk");
        when(personRepository.findByName(name)).thenReturn(Optional.of(melek));

        //when
        Person person = personService.findByName(name);

        //then
        verify(personRepository).findByName(name);
        assertThat(person.getName()).isEqualTo("Melek");
        assertThat(person.getSurname()).isEqualTo("Tamtürk");
    }

    @Test
    public void it_should_throw_exception_when_person_does_not_exist_with_given_name() {
        //given
        String name = "Irem";

        when(personRepository.findByName(name)).thenReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> personService.findByName(name));

        //then
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        RuntimeException exception = (RuntimeException) throwable;
        assertThat(exception.getMessage()).isEqualTo("Irem not found");
    }
}