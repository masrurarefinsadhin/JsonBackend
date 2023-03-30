package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.CustomUserDetails;
import com.jsonbook.Json.Book.entity.User;
import com.jsonbook.Json.Book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(s));
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + s));
        return user.map(CustomUserDetails::new).get();
    }
}
