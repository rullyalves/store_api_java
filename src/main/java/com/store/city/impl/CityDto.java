package com.store.city.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Data Tranfer Object
public class CityDto {

    @Size(message = "O nome do estado não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O nome do estado não pode pode ser vazio")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
