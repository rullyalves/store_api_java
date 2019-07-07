package com.store.state;

import java.util.Collection;

import com.store.state.impl.State;
import com.store.state.impl.StateDto;


public interface IStateDao {

    Collection<State> findAll();

    Collection<State> findByRegionId(Long regionId);

    State findById(Long id);

    Long save(Long regionId,StateDto StateDto);

    void update(StateDto StateDto);

    void delete(Long id);

}

