package com.example.securityredirectservice.controllers;

import com.example.securityredirectservice.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/statuses")
@RequiredArgsConstructor
public class RedirectStatusController {
    private final StatusService statusService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> getAll(){
        return statusService.getAllStatuses();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KITCHEN')")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id){
        return statusService.getStatus(id);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addNewStatus(@RequestBody Object value){
        return statusService.addNewStatus(value);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@RequestBody Object statusDTO){
        return statusService.update(statusDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        return statusService.delete(id);
    }
}
