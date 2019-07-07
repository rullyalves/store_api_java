package com.store.address.impl;

import java.util.Collection;
import com.store.exceptions.ResourceNotFoundException;
import com.store.address.IAddressDao;
import com.store.address.IAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    @Autowired
    IAddressDao addressDao;

    @Override
    public Collection<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public Collection<Address> findByCityId(Long cityId) {
        return addressDao.findByCityId(cityId);
    }

    @Override
    public Collection<Address> findByCustomerId(Long customerId) {
        return addressDao.findByCustomerId(customerId);
    }

    @Override
    public Address findById(Long id) {
        try {
            return addressDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A Address de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(Long regionId, AddressCreateDto addressDto) {
        return addressDao.save(regionId, addressDto);
    }

    @Override
    public void update(Long customerId, AddressDto addressDto) {
        addressDao.update(customerId, addressDto);
    }

    @Override
    public void delete(Long id) {
        addressDao.delete(id);
    }

}
