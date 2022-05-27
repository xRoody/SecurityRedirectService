package com.example.securityredirectservice.repositories;

import com.example.securityredirectservice.entities.Other;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtherRepo extends JpaRepository<Other,Long> {
    Optional<Other> findByLogin(String login);
}
