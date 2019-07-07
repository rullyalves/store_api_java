package com.store.state.impl;

import java.util.Collection;
import com.store.exceptions.ResourceNotFoundException;
import com.store.state.IStateDao;
import com.store.state.IStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService implements IStateService {

    @Autowired
    IStateDao stateDao;

    @Override
    public Collection<State> findAll() {
        return stateDao.findAll();
    }

    @Override
    public Collection<State> findByRegionId(Long regionId) {
        return stateDao.findByRegionId(regionId);
    }

    @Override
    public State findById(Long id) {
        try {
            return stateDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A State de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(Long regionId,StateDto stateDto) {
        return stateDao.save(regionId,stateDto);
    }

    @Override
    public void update(StateDto stateDto) {
        stateDao.update(stateDto);
    }

    @Override
    public void delete(Long id) {
        stateDao.delete(id);
    }



}
