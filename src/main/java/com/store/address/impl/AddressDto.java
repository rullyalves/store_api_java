package com.store.address.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Data Tranfer Object
public class AddressDto {

    @Size(message = "O número do cep não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O número do cep não pode pode ser vazio")
    private String zipCode;

    @Size(message = "O nome da rua não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O nome da rua não pode ser vazio")
    private String street;

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
