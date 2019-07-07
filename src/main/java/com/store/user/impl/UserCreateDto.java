package com.store.user.impl;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserCreateDto {

    @NotEmpty(message = "O email não pode pode ser vazio")
    @Email(message = "O email está em um formato inválido")
    private String email;

    @Size(message = "A senha não deve conter deve ser menor que 6 dígitos e nem maior que 32 dígitos",min = 6,max = 32)
    @NotEmpty(message = "A senha não pode estar em branco")
    private String password;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}