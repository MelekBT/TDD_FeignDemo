package com.example.demo.controller;

import com.example.demo.client.PersonApiClient;
import com.example.demo.model.Person;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonApiClientTest {

    @ClassRule
    public static WireMockClassRule instanceRule = new WireMockClassRule(7055);

    @Autowired
    PersonApiClient personApiClient;



    @Test
    public void it_should_return_personCatalog_by_id() {
        //given
        Person person = new Person("Melek", "Tamtürk");

        instanceRule.stubFor(get(urlPathMatching("/persons/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"name\": Melek,\n" +
                                "   \"surname\": \"Tamtürk\"" + "}")));

        //when
        Person personResponse = personApiClient.getPersonById(1L);

        //then
        assertThat(personResponse.getName()).isEqualTo("Melek");
        assertThat(personResponse.getSurname()).isEqualTo("Tamtürk");
    }

    @Test
    public void it_should_return_personCatalogs_by_name() {
        //given
        Person person = new Person("Melek", "Tamtürk");

        instanceRule.stubFor(get(urlEqualTo("/persons/by-name?name=Melek"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")));

        //when
        Set<Person> personResponses = personApiClient.getPersonByName("Melek");

        //then
       Iterator<Person> iterator = personResponses.iterator();
        Person personResponse = iterator.next();
        assertThat(personResponse.getName()).isEqualTo("Melek");
        assertThat(personResponse.getSurname()).isEqualTo("Tamtürk");
    }

}