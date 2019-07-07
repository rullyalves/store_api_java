package com.store.region;

import java.util.Collection;

import com.store.region.impl.Region;
import com.store.region.impl.RegionDto;


public interface IRegionDao {

    Collection<Region> findAll();

    Region findById(Long id);

    Long save(RegionDto regionDto);

    void update(RegionDto regionDto);

    void delete(Long id);

}

