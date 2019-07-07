package com.store.state;

import java.util.Collection;

import com.store.state.impl.State;
import com.store.state.impl.StateDto;

public interface IStateService {

    Collection<State> findAll();

    Collection<State> findByRegionId(Long regionId);

    State findById(Long id);

    Long save(Long id,StateDto stateDto);

    void update(StateDto stateDto);

    void delete(Long id);

}