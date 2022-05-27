package com.example.securityredirectservice.controllers;

import com.example.securityredirectservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class RedirectOrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> getAll(){
        return orderService.getAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id){
        return orderService.getById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        return orderService.delete(id);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Void> changeStatus(@PathVariable("id") Long id, @RequestBody Long statusId){
        return orderService.changeStatus(id, statusId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> save(@RequestBody Object orderDTO){
        //System.out.println(orderDTO);
        //return null;
        return orderService.add(orderDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@RequestBody Object orderDTO){
        return orderService.update(orderDTO, id);
    }

    @GetMapping("/{id}/getPrice")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> getPrice(@PathVariable("id") Long id){
        return orderService.getPrice(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> trueGetAll(){
        return ResponseEntity.ok(orderService.trueGetAll());
    }
}
