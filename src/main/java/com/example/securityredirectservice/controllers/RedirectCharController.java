package com.example.securityredirectservice.controllers;

import com.example.securityredirectservice.services.CharService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/characteristics")
@RequiredArgsConstructor
public class RedirectCharController {
    private final CharService charService;
    private ReentrantLock lock=new ReentrantLock();

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return charService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addNewChar(Object characteristicDTO) {
        return charService.addCharacteristic(characteristicDTO);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> update(Object characteristicDTO) {
        return charService.update(characteristicDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteChar(@PathVariable("id") Long id) {
        return charService.delete(id);
    }

    @SneakyThrows
    @PatchMapping
    public ResponseEntity<Void> getFromStorage(@RequestBody Map<String, Integer> map){
            ResponseEntity<Void> entity= charService.getFromService(map);
            entity.getStatusCodeValue();
            return entity;
    }
}
