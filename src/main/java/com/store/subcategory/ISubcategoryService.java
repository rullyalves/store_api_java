package com.store.subcategory;

import java.util.Collection;

import com.store.subcategory.impl.Subcategory;
import com.store.subcategory.impl.SubcategoryDto;

public interface ISubcategoryService{

    Collection<Subcategory> findByCategoryId(Long categoryId);

    Collection<Subcategory> findAll();

    Subcategory findById(Long id);

    Long save(Long categoryId,SubcategoryDto subcategoryDto);

    void update(SubcategoryDto subcategoryDto);

    void delete(Long id);
    
}