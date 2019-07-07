package com.store.role.impl;

import java.util.Collection;
import javax.validation.Valid;
import com.store.role.IRoleService;

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
public class RoleController {

    @Autowired
    IRoleService roleService;

    // 200 OK
    @GetMapping("api/roles")
    ResponseEntity<Collection<Role>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    // 200 OK
    @GetMapping("api/roles/{id}")
    ResponseEntity<Role> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/roles")
    ResponseEntity<?> save(
        @Valid @RequestBody RoleDto roleCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = roleService.save(roleCreateDto);
        UriComponents urlCreated = uri.path("v1/api/roles/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/roles/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody RoleDto roleDto) {
        roleService.update(roleDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/roles/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}