package com.store.address;

import java.util.Collection;

import com.store.address.impl.Address;
import com.store.address.impl.AddressCreateDto;
import com.store.address.impl.AddressDto;


public interface IAddressDao {

    Collection<Address> findAll();

    Collection<Address> findByCityId(Long cityId);

    Collection<Address> findByCustomerId(Long customerId);

    Address findById(Long id);

    Long save(Long customerId,AddressCreateDto AddressDto);

    void update(Long customerId,AddressDto AddressDto);

    void delete(Long id);

}

