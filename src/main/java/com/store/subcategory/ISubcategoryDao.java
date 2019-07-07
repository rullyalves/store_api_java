package com.store.subcategory;

import java.util.Collection;
import com.store.subcategory.impl.Subcategory;
import com.store.subcategory.impl.SubcategoryDto;

public interface ISubcategoryDao {

    Collection<Subcategory> findByCategoryId(Long categoryId);

    Collection<Subcategory> findAll();

    Subcategory findById(Long id);

    Long save(Long categoryId,SubcategoryDto categoryDto);

    void update(SubcategoryDto categoryDto);

    void delete(Long id);

}