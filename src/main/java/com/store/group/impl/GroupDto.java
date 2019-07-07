package com.store.group.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Data Tranfer Object
public class GroupDto {

    @Size(message = "O nome da categoria não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O nome da permissão não pode pode ser vazio")
    private String name;

    @Size(message = "O nome da categoria não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "A descrição da permissão não pode ser vazia")
    private String description;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
