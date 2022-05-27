package com.example.securityredirectservice.controllers;

import com.example.securityredirectservice.services.OfferService;
import com.example.securityredirectservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class RedirectOfferController {
    private final OfferService offerService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER','KITCHEN')")
    public ResponseEntity<Object> getAllOffers(){
        return offerService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> getOfferById(@PathVariable("id") Long id){
        return offerService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addOffer(@RequestBody Object offer){
        return offerService.add(offer);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        return offerService.delete(id);
    }

    @GetMapping("/{id}/characteristics")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> getCharsByOfferId(@PathVariable("id") Long id) {
        return offerService.getAllChars(id);
    }

    @GetMapping("/{id}/categoryAndPrice")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN', 'USER')")
    public ResponseEntity<Object> getCategoryAndPrice(@PathVariable("id") Long id) {
        return offerService.getCategoryAndPrice(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateOffer(Object offerDTO) {
        return offerService.updateOffer(offerDTO);
    }
}
