package com.store.group.impl;

import java.util.Collection;
import javax.validation.Valid;
import com.store.group.IGroupService;

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
public class GroupController {

    @Autowired
    private IGroupService GroupService;

    // 200 OK
    @GetMapping("api/groups")
    ResponseEntity<Collection<Group>> findAll() {
        return ResponseEntity.ok(GroupService.findAll());
    }

    // 200 OK
    @GetMapping("api/groups/{id}")
    ResponseEntity<Group> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(GroupService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/groups")
    ResponseEntity<?> save(
        @Valid @RequestBody GroupDto GroupCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = GroupService.save(GroupCreateDto);
        UriComponents urlCreated = uri.path("v1/api/groups/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/groups/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody GroupDto GroupDto) {
        GroupService.update(GroupDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/groups/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        GroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

}