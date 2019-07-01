package com.example.demo.client;

import com.example.demo.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "person-api-service", url = "${client.url.person-catalog-api}")
public interface PersonApiClient {

    @GetMapping("/persons/{id}")
    Person getPersonById(@PathVariable("id") Long id);

    @RequestMapping("/persons/by-name")
    Set<Person> getPersonByName(@RequestParam("name") String name);
}
