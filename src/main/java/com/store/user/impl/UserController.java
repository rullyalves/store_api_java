package com.store.user.impl;

import java.util.Collection;

import javax.validation.Valid;

import com.store.user.IUserFacade;
import com.store.user.IUserService;

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
public class UserController {

    @Autowired
    IUserFacade UserFacade;

    @Autowired
    IUserService UserService;

    // 200 OK
    @GetMapping("api/users")
    ResponseEntity<Collection<User>> findAll() {
        return ResponseEntity.ok(UserService.findAll());
    }

    // 200 OK
    @GetMapping("api/users/{id}")
    ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(UserService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/users")
    ResponseEntity<?> save(
        @Valid @RequestBody UserCreateDto UserCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = UserFacade.save(UserCreateDto);
        UriComponents urlCreated = uri.path("v1/api/users/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/users/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody UserDto UserDto) {
        UserService.update(UserDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/users/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        UserService.delete(id);
        return ResponseEntity.noContent().build();
    }

}