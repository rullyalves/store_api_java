package com.store.sex.impl;

import java.util.Collection;
import com.store.exceptions.ResourceNotFoundException;
import com.store.sex.ISexDao;
import com.store.sex.ISexService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SexService implements ISexService {

    @Autowired
    ISexDao sexDao;

    @Override
    public Collection<Sex> findAll() {
        return sexDao.findAll();
    }

    @Override
    public Sex findById(Long id) {
        try {
            return sexDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A Sex de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(SexDto SexDto) {
        return sexDao.save(SexDto);
    }

    @Override
    public void update(SexDto SexDto) {
        sexDao.update(SexDto);
    }

    @Override
    public void delete(Long id) {
        sexDao.delete(id);
    }

}
