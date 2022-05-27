package com.example.securityredirectservice.services;

import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<Object> getAll();

    ResponseEntity<Object> getById(Long id);

    ResponseEntity<Void> delete(Long id);

    ResponseEntity<Void> changeStatus(Long id, Long statusId);

    ResponseEntity<Object> add(Object orderDTO);

    ResponseEntity<Object> update(Object orderDTO, Long id);

    ResponseEntity<Object> getPrice(Long id);

    Object trueGetAll();
}
