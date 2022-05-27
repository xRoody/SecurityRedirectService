package com.example.securityredirectservice.serviceImpls;

import com.example.securityredirectservice.entities.Other;
import com.example.securityredirectservice.repositories.OtherRepo;
import com.example.securityredirectservice.services.OtherService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class OtherServiceImpl implements OtherService {
    private OtherRepo otherRepo;
    private BCryptPasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Other other=otherRepo.findByLogin(username).orElseThrow(()->new UsernameNotFoundException(username));
        Collection<GrantedAuthority> authorities= Stream.of(other.getRole()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(username, other.getPassword(), authorities);
    }

    @Override
    public void save(Other other) {
        other.setPassword(encoder.encode(other.getPassword()));
        otherRepo.save(other);
    }
}
