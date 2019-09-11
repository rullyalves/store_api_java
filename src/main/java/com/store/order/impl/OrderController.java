package com.store.order.impl;

import com.store.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RequestMapping("v1")
@RestController
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @GetMapping("/api/orders")
    ResponseEntity<Collection<Order>> findAll(){
        return ResponseEntity.ok(iOrderService.findAll());
    }

    @GetMapping("/api/orders/{id}")
    ResponseEntity<Order> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(iOrderService.findById(id));
    }

    /*
    @PostMapping("api/orders")
    ResponseEntity save(@Valid @RequestBody) {

    }
    */

    @DeleteMapping("/api/orders/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) {
        iOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
