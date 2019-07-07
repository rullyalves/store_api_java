package com.store.subcategory.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Data Tranfer Object
public class SubcategoryDto {

    @Size(message = "O nome da subcategoria não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O nome da subcategoria não pode pode ser vazio")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}