package com.store.region.impl;

import java.util.Collection;
import javax.validation.Valid;
import com.store.region.IRegionService;

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
public class RegionController {

    @Autowired
    IRegionService regionService;

    // 200 OK
    @GetMapping("api/regions")
    ResponseEntity<Collection<Region>> findAll() {
        return ResponseEntity.ok(regionService.findAll());
    }

    // 200 OK
    @GetMapping("api/regions/{id}")
    ResponseEntity<Region> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(regionService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/regions")
    ResponseEntity<?> save(
        @Valid @RequestBody RegionDto RegionCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = regionService.save(RegionCreateDto);
        UriComponents urlCreated = uri.path("v1/api/regions/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/regions/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody RegionDto RegionDto) {
        regionService.update(RegionDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/regions/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        regionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}