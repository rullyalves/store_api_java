package com.store.category.impl;

import java.util.Collection;

import com.store.category.ICategoryFacade;
import com.store.category.ICategoryService;
import com.store.subcategory.ISubcategoryService;
import com.store.subcategory.impl.SubcategoryDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryFacade implements ICategoryFacade {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISubcategoryService subcategoryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(CategoryCreateDto categoryCreateDto) {
        try {
            // separando em uma DTO de categoria
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(categoryCreateDto.getName());

            // separando em uma lista de DTO de subcategoria
            Collection<SubcategoryDto> subcategories = categoryCreateDto.getSubcategories();

            // salvando a categoria
            Long categoryId = categoryService.save(categoryDto);
            // percorrendo a lista de subcategorias
            // e salvando todas
            if (!subcategories.isEmpty() || subcategories != null) {
                subcategories.stream().forEach(subcategoryDto -> {
                    subcategoryService.save(categoryId, subcategoryDto);
                });
            }
            return categoryId;

        } catch (Exception e) {
            throw e;
        }
    }

}