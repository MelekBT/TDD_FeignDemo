package com.example.demo.controller;

import com.example.demo.client.PersonApiClient;
import com.example.demo.converter.PersonCatalogConverter;
import com.example.demo.model.Person;
import com.example.demo.model.PersonCatalog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonCatalogController.class)
@AutoConfigureMockMvc
public class PersonCatalogControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonApiClient personApiClient;

    @MockBean
    private PersonCatalogConverter personCatalogConverter;

    @Test
    public void it_should_get_person_catalog_by_id() throws Exception {
        //given
        Person person = new Person();
        person.setName("Melek");
        person.setSurname("Tamtürk");
        when(personApiClient.getPersonById(20L)).thenReturn(person);

        PersonCatalog personCatalog = new PersonCatalog("Melek", "Tamtürk");
        when(personCatalogConverter.apply(person)).thenReturn(personCatalog);

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/person-catalog/20"));

        //then
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Melek"))
                .andExpect(jsonPath("$.surname").value("Tamtürk"));
    }

    @Test
    public void it_should_get_person_catalogs_by_name() throws Exception {
        //given
        Person person = new Person("Melek","Tamtürk");
        Set<Person> persons = Sets.newSet(person);
        when(personApiClient.getPersonByName("Melek")).thenReturn(persons);

        PersonCatalog personCatalog = new PersonCatalog("Melek", "Tamtürk");
        when(personCatalogConverter.applySet(persons)).thenReturn(Sets.newSet(personCatalog));

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/person-catalog/name?name=Melek"));

        //then
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Melek"))
                .andExpect(jsonPath("$.surname").value("Tamtürk"));
    }
}