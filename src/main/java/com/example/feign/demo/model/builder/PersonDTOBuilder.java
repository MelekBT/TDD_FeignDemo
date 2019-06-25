package com.example.feign.demo.model.builder;

import com.example.feign.demo.model.response.PersonDTO;

public final class PersonDTOBuilder {
    private String name;
    private String surname;

    private PersonDTOBuilder() {
    }

    public static PersonDTOBuilder aPersonDTO() {
        return new PersonDTOBuilder();
    }

    public PersonDTOBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonDTOBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonDTO build() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(name);
        personDTO.setSurname(surname);
        return personDTO;
    }
}
