package com.store.subcategory.impl;

import java.util.Collection;
import com.store.subcategory.ISubcategoryDao;
import com.store.subcategory.ISubcategoryService;
import com.store.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryService implements ISubcategoryService {

    @Autowired
    ISubcategoryDao categoryDao;

    @Override
    public Collection<Subcategory> findByCategoryId(Long categoryId) {
        return categoryDao.findByCategoryId(categoryId);
    }

    @Override
    public Collection<Subcategory> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Subcategory findById(Long id) {
        try {
            return categoryDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A subcategoria de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(Long categoryId,SubcategoryDto categoryDto) {
        return categoryDao.save(categoryId,categoryDto);
    }

    @Override
    public void update(SubcategoryDto categoryDto) {
        categoryDao.update(categoryDto);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

}
