package com.example.securityredirectservice.controllers;

import com.example.securityredirectservice.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class RedirectOfferOrderCardController {
    private final CardService offerOrderCardService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> getAll(){
        return offerOrderCardService.getAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id){
        return offerOrderCardService.getById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        return offerOrderCardService.delete(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> add(@RequestBody Object offerOrderCardDTO){
        return offerOrderCardService.add(offerOrderCardDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@RequestBody Object offerOrderCardDTO){
        return offerOrderCardService.update(offerOrderCardDTO);
    }


    @GetMapping("/{id}/getInfo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> getInfo(@PathVariable("id") Long id){
        return offerOrderCardService.getInfo(id);
    }

    @PostMapping("/addChange")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> saveChangedCharacteristic(@RequestBody Object characteristicDTO){
        return offerOrderCardService.saveChange(characteristicDTO);
    }
}
