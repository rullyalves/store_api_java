package com.store.category.impl;

import java.util.Collection;
import com.store.category.ICategoryDao;
import com.store.category.ICategoryService;
import com.store.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryDao categoryDao;

    @Override
    public Collection<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        try {
            return categoryDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A categoria de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(CategoryDto categoryDto) {
        return categoryDao.save(categoryDto);
    }

    @Override
    public void update(CategoryDto categoryDto) {
        categoryDao.update(categoryDto);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

}
