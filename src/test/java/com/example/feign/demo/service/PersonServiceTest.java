package com.example.feign.demo.service;

import com.example.feign.demo.model.Person;
import com.example.feign.demo.repository.PersonRepository;
import com.example.feign.demo.repository.PersonRepositoryHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        RuntimeException exception = (RuntimeException) throwable;
        assertThat(exception.getMessage()).isEqualTo("Irem not found");
    }
}