package com.store.subcategory.impl;

import java.util.Collection;
import javax.validation.Valid;
import com.store.subcategory.ISubcategoryService;
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
public class SubcategoryController {

    @Autowired
    ISubcategoryService categoryService;

    // 200 OK
    @GetMapping("api/subcategories")
    ResponseEntity<Collection<Subcategory>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // 200 OK
    @GetMapping("api/subcategories/{id}")
    ResponseEntity<Subcategory> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    // 200 OK
    @GetMapping("api/categories/{categoryId}/subcategories")
    ResponseEntity<Collection<Subcategory>> findByCategoryId(
        @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(categoryService.findByCategoryId(categoryId));
    }

    // 201 CREATED
    @PostMapping("api/categories/{categoryId}/subcategories")
    ResponseEntity<?> save(
        @PathVariable("categoryId") Long categoryId, 
        @Valid @RequestBody SubcategoryDto categoryDto,
        UriComponentsBuilder uri) {
        Long id = categoryService.save(categoryId, categoryDto);
        UriComponents urlCreated = uri.path("v1/api/subcategories/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();
    }

    // 203 NO CONTENT
    @PatchMapping("api/subcategories/{id}")
    ResponseEntity<?> updateById(
        @PathVariable("id") Long id,
        @Valid @RequestBody SubcategoryDto categoryDto) {
        categoryService.update(categoryDto);
        return ResponseEntity.noContent().build();
    }

    // 203 NO CONTENT
    @DeleteMapping("api/subcategories/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}