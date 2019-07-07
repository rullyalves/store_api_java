package com.store.region.impl;

import java.util.Collection;
import com.store.exceptions.ResourceNotFoundException;
import com.store.region.IRegionDao;
import com.store.region.IRegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService implements IRegionService {

    @Autowired
    IRegionDao regionDao;

    @Override
    public Collection<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public Region findById(Long id) {
        try {
            return regionDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A Region de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(RegionDto RegionDto) {
        return regionDao.save(RegionDto);
    }

    @Override
    public void update(RegionDto RegionDto) {
        regionDao.update(RegionDto);
    }

    @Override
    public void delete(Long id) {
        regionDao.delete(id);
    }

}
