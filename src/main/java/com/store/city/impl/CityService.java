package com.store.city.impl;

import java.util.Collection;
import com.store.exceptions.ResourceNotFoundException;
import com.store.city.ICityDao;
import com.store.city.ICityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Autowired
    ICityDao CityDao;

    @Override
    public Collection<City> findAll() {
        return CityDao.findAll();
    }

    @Override
    public Collection<City> findByStateId(Long stateId) {
        return CityDao.findByStateId(stateId);
    }

    @Override
    public City findById(Long id) {
        try {
            return CityDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A City de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(Long stateId, CityDto CityDto) {
        return CityDao.save(stateId, CityDto);
    }

    @Override
    public void update(CityDto CityDto) {
        CityDao.update(CityDto);
    }

    @Override
    public void delete(Long id) {
        CityDao.delete(id);
    }

}
