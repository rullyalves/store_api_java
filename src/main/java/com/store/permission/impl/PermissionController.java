package com.store.permission.impl;

import com.store.permission.IPermissionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("v1")
@RestController
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @GetMapping("/api/permissions")
    ResponseEntity<Collection<Permission>> findAll(){
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping("/api/permissions/{id}")
    ResponseEntity<Permission> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(permissionService.findById(id));
    }

    @PostMapping("api/permissions")
    ResponseEntity save(
            @Valid @RequestBody PermissionDto permission,
            UriComponentsBuilder uri
    ){


        Long id = permissionService.save(permission);

        UriComponents uriBuilder = uri.path("v1/api/permissions/{id}").buildAndExpand(id);

        return ResponseEntity.created(uriBuilder.toUri()).build();
    }


    @PatchMapping("api/permissions/id")
    ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody PermissionDto permission) {
        permissionService.update(id, permission);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("api/permissions/{id}")
    ResponseEntity delete(@PathVariable("id") Long id){
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
