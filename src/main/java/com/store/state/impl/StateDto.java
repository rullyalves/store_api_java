package com.store.state.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Data Tranfer Object
public class StateDto {

    @Size(message = "O nome do estado não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O nome do estado não pode pode ser vazio")
    private String name;

    @Size(message = "O nome do estado não pode conter menos de 3 letras", min = 1)
    @NotEmpty(message = "O nome da estado não pode pode ser vazio")
    private CharSequence symbol;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharSequence getSymbol() {
        return this.symbol;
    }

    public void setSymbol(CharSequence symbol) {
        this.symbol = symbol;
    }


}
