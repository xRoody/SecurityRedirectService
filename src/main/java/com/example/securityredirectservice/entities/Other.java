package com.example.securityredirectservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "other")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Other {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String role;

    public Other(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
