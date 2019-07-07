package com.store.category.impl;

import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.store.subcategory.impl.SubcategoryDto;

public class CategoryCreateDto {

    @Size(message = "O nome da categoria não pode conter menos de 3 letras", min = 3)
    @NotEmpty(message = "O nome da categoria não pode pode ser vazio")
    private String name;
    
    private Collection<SubcategoryDto> subcategories = new ArrayList<SubcategoryDto>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SubcategoryDto> getSubcategories() {
        return this.subcategories;
    }

    public void setSubcategories(Collection<SubcategoryDto> subcategories) {
        this.subcategories = subcategories;
    }

}