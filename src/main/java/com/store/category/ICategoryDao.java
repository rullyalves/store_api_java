package com.store.category;

import java.util.Collection;
import com.store.category.impl.Category;
import com.store.category.impl.CategoryDto;

public interface ICategoryDao {

    Collection<Category> findAll();

    Category findById(Long id);

    Long save(CategoryDto categoryDto);

    void update(CategoryDto categoryDto);

    void delete(Long id);

}

