package com.example.feign.demo.model.request;

import javax.validation.constraints.NotBlank;

public class PersonCreateRequest {

    @NotBlank(message = "{person.api.person.create.request.name.is.blank}")
    private String name;

    @NotBlank(message = "{person.api.person.create.request.surname.is.blank}")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
