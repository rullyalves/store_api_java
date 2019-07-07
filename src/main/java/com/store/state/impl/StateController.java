package com.store.state.impl;

import java.util.Collection;
import javax.validation.Valid;
import com.store.state.IStateService;

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
public class StateController {

    @Autowired
    IStateService stateService;

    // 200 OK
    @GetMapping("api/states")
    ResponseEntity<Collection<State>> findAll() {
        return ResponseEntity.ok(stateService.findAll());
    }

    @GetMapping("api/regions/{regionId}/states")
    ResponseEntity<Collection<State>> findByRegionId(
        @PathVariable("regionId") Long regionId) {
        return ResponseEntity.ok(stateService.findByRegionId(regionId));
    }

    // 200 OK
    @GetMapping("api/states/{id}")
    ResponseEntity<State> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(stateService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/regions/{regionId}/states")
    ResponseEntity<?> save(
         @PathVariable("regionId") Long regionId,
         @Valid @RequestBody StateDto stateCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = stateService.save(regionId,stateCreateDto);
        UriComponents urlCreated = uri.path("v1/api/states/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/states/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody StateDto StateDto) {
        stateService.update(StateDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/states/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}