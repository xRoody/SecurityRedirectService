package com.example.securityredirectservice.services;

import com.example.securityredirectservice.entities.Other;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OtherService extends UserDetailsService {
    void save(Other other);
}
