package com.store.user.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Data Tranfer Object
public class UserDto {

    @Size(message = "O nome da categoria não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O nome da categoria não pode pode ser vazio")
    private String name;


}