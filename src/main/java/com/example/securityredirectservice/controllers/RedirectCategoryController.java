package com.example.securityredirectservice.controllers;

import com.example.securityredirectservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class RedirectCategoryController {
    private final CategoryService categoryService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> getAll(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> add(@RequestBody Object categoryDTO){
        return categoryService.add(categoryDTO);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object>  update(@RequestBody Object categoryDTO){
        return categoryService.update(categoryDTO);
    }

    @GetMapping("/{id}/allOffers")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> getAllOffersFrom(@PathVariable("id") Long id){
        return categoryService.getAllOffersByCategory(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object>  delete(@PathVariable("id") Long id){
        return categoryService.delete(id);
    }
}
