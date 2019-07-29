package com.store.address.impl;

import javax.validation.Valid;
import com.store.address.IAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;


@RestController
@RequestMapping("v1")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    // 200 OK
    @GetMapping("api/addresses")
    ResponseEntity<Collection<Address>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("api/cities/{cityId}/addresses")
    ResponseEntity<Collection<Address>> findByCityId(
        @PathVariable("cityId") Long cityId) {
        return ResponseEntity.ok(addressService.findByCityId(cityId));
    }

    @GetMapping("api/customers/{customerId}/addresses")
    ResponseEntity<Collection<Address>> findByCustomerId(
        @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(addressService.findByCityId(customerId));
    }

    // 200 OK
    @GetMapping("api/addresses/{id}")
    ResponseEntity<Address> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/customers/{customerId}/addresses")
    ResponseEntity<?> save(
         @PathVariable("customerId") Long customerId,
         @Valid @RequestBody AddressCreateDto addressCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = addressService.save(customerId,addressCreateDto);
        UriComponents urlCreated = uri.path("v1/api/addresses/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/addresses/{id}")
    ResponseEntity<?> updateById(
        @PathVariable("id") Long id, 
        @Valid @RequestBody AddressDto addressDto) {
        addressService.update(id,addressDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/addresses/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}