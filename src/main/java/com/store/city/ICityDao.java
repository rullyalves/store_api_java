package com.store.city;

import java.util.Collection;

import com.store.city.impl.City;
import com.store.city.impl.CityDto;


public interface ICityDao {

    Collection<City> findAll();

    Collection<City> findByStateId(Long stateId);

    City findById(Long id);

    Long save(Long stateId,CityDto CityDto);

    void update(CityDto CityDto);

    void delete(Long id);

}

