package com.store.city.impl;

public class City {
    
    private Long id;
    private String name;
    private CharSequence symbol;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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