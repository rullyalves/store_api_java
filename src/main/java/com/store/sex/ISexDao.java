package com.store.sex;

import java.util.Collection;

import com.store.sex.impl.Sex;
import com.store.sex.impl.SexDto;


public interface ISexDao {

    Collection<Sex> findAll();

    Sex findById(Long id);

    Long save(SexDto sexDto);

    void update(SexDto sexDto);

    void delete(Long id);

}

