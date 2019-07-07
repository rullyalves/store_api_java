package com.store.city.impl;

import java.util.Collection;
import javax.validation.Valid;
import com.store.city.ICityService;

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


@RestController
@RequestMapping("v1")
public class CityController {

    @Autowired
    ICityService CityService;

    // 200 OK
    @GetMapping("api/city")
    ResponseEntity<Collection<City>> findAll() {
        return ResponseEntity.ok(CityService.findAll());
    }

    @GetMapping("api/states/{stateId}/city")
    ResponseEntity<Collection<City>> findBystateId(
        @PathVariable("stateId") Long stateId) {
        return ResponseEntity.ok(CityService.findByStateId(stateId));
    }

    // 200 OK
    @GetMapping("api/city/{id}")
    ResponseEntity<City> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(CityService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/states/{stateId}/city")
    ResponseEntity<?> save(
         @PathVariable("stateId") Long stateId,
         @Valid @RequestBody CityDto CityCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = CityService.save(stateId,CityCreateDto);
        UriComponents urlCreated = uri.path("v1/api/city/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/city/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody CityDto CityDto) {
        CityService.update(CityDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/city/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        CityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}