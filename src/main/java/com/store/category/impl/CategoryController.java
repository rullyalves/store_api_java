package com.store.category.impl;

import java.util.Collection;

import javax.validation.Valid;

import com.store.category.ICategoryFacade;
import com.store.category.ICategoryService;

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
public class CategoryController {

    @Autowired
    ICategoryFacade categoryFacade;

    @Autowired
    ICategoryService categoryService;

    // 200 OK
    @GetMapping("api/categories")
    ResponseEntity<Collection<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // 200 OK
    @GetMapping("api/categories/{id}")
    ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    // 201 CREATED
    @PostMapping("api/categories")
    ResponseEntity<?> save(
        @Valid @RequestBody CategoryCreateDto categoryCreateDto,
         UriComponentsBuilder uri
         ) {
        Long id = categoryFacade.save(categoryCreateDto);
        UriComponents urlCreated = uri.path("v1/api/categories/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/categories/{id}")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody CategoryDto categoryDto) {
        categoryService.update(categoryDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/categories/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}