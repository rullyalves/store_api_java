package com.store.city;

import java.util.Collection;

import com.store.city.impl.City;
import com.store.city.impl.CityDto;

public interface ICityService {

    Collection<City> findAll();

    Collection<City> findByStateId(Long stateId);

    City findById(Long id);

    Long save(Long stateId,CityDto cityDto);

    void update(CityDto cityDto);

    void delete(Long id);

}