package com.example.securityredirectservice.services;

import org.springframework.http.ResponseEntity;

public interface OfferService {
    ResponseEntity<Object> getAll();

    ResponseEntity<Object> getById(Long id);

    ResponseEntity<Object> add(Object offer);

    ResponseEntity<Object> delete(Long id);

    ResponseEntity<Object> getAllChars(Long id);

    ResponseEntity<Object> getCategoryAndPrice(Long id);

    ResponseEntity<Object> updateOffer(Object offerDTO);
}
