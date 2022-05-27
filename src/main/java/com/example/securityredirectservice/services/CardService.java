package com.example.securityredirectservice.services;

import org.springframework.http.ResponseEntity;

public interface CardService {
    ResponseEntity<Object> getAll();

    ResponseEntity<Object> getById(Long id);

    ResponseEntity<Object> delete(Long id);

    ResponseEntity<Object> add(Object offerOrderCardDTO);

    ResponseEntity<Object> update(Object offerOrderCardDTO);

    ResponseEntity<Object> getInfo(Long id);

    ResponseEntity<Object> saveChange(Object characteristicDTO);
}
