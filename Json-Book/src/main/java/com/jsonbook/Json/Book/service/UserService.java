package com.jsonbook.Json.Book.service;
import com.jsonbook.Json.Book.entity.User;
import com.jsonbook.Json.Book.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);


}
