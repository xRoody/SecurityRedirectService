package com.example.securityredirectservice.services;

import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<Object> getAllCategories();

    ResponseEntity<Object> getById(Long id);

    ResponseEntity<Object> add(Object categoryDTO);

    ResponseEntity<Object> update(Object categoryDTO);

    ResponseEntity<Object> getAllOffersByCategory(Long id);
    ResponseEntity<Object> delete(Long id);
}
