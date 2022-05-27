package com.example.securityredirectservice.services;

import com.example.securityredirectservice.dtos.BodyExceptionWrapper;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CharService {
    ResponseEntity<Object> getById(Long id);

    ResponseEntity<Object> addCharacteristic(Object characteristicDTO);

    ResponseEntity<Object> update(Object characteristicDTO);

    ResponseEntity<Object> delete(Long id);

    ResponseEntity<Void> getFromService(Map<String, Integer> map);
}
