package com.example.securityredirectservice.services;

import org.springframework.http.ResponseEntity;

public interface StatusService {
    ResponseEntity<Object> getAllStatuses();

    ResponseEntity<Object> getStatus(Long id);

    ResponseEntity<Object> addNewStatus(Object value);

    ResponseEntity<Object> update(Object statusDTO);

    ResponseEntity<Object> delete(Long id);
}
