package com.store.sex.impl;

import java.util.Collection;
import javax.validation.Valid;
import com.store.sex.ISexService;

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
public class SexController {

    @Autowired
    ISexService sexService;

    // 200 OK
    @GetMapping("api/sexes")
    ResponseEntity<Collection<Sex>> findAll() {
        return ResponseEntity.ok(sexService.findAll());
    }

    // 200 OK
    @GetMapping("api/sexes/{id}")
    ResponseEntity<Sex> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sexService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/sexes")
    ResponseEntity<?> save(
        @Valid @RequestBody SexDto SexCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = sexService.save(SexCreateDto);
        UriComponents urlCreated = uri.path("v1/api/sexes/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/sexes/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody SexDto SexDto) {
        sexService.update(SexDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/sexes/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        sexService.delete(id);
        return ResponseEntity.noContent().build();
    }

}