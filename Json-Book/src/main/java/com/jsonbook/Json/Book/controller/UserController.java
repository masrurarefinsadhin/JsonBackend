package com.jsonbook.Json.Book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.jsonbook.Json.Book.entity.SimpleUser;
import com.jsonbook.Json.Book.service.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsonbook.Json.Book.repository.UserRepository;
import com.jsonbook.Json.Book.entity.User;

@CrossOrigin(origins ={ "http://localhost:4200"})
@RestController
@RequestMapping("/api/v2/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user) {
        //return userRepository.save(user);
        Map<String,Object> response = new HashMap<String,Object>();
        HttpStatus status = HttpStatus.OK;

        if (userRepository.existsByEmail(user.getEmail())) {
            response.put("message", "Failed");
            response.put("description", "Email already used.");
            status = HttpStatus.NOT_ACCEPTABLE;
        } else {
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            response.put("message", "Success");
            response.put("description", "Registered");
        }

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {

        Map<String,Object> response = new HashMap<String,Object>();
        User use = userRepository.findByEmail(userRequest.getEmail());
        HttpStatus status = HttpStatus.OK;


        if (userRepository.existsByEmail(userRequest.getEmail()) && use != null) {
            if (encoder.matches(userRequest.getPassword(), use.getPassword())) {

                String jwt = use.getEmail() + "+token";

                response.put("accessToken", jwt);
                response.put("email", use.getEmail());
                response.put("id",use.getId());
                response.put("description", "Successful");
            } else {
                response.put("message", "Failed");
                response.put("description", "Wrong password.");
                status = HttpStatus.UNAUTHORIZED;
            }
        } else {
            response.put("message", "Failed");
            response.put("description", "Invalid User");
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(response, status);
    }


    //get all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/list-of-users")
    public Map<Long,String> getAllListOfUsers(){
        /*return userRepository.findAll().stream().map(user -> new SimpleUser(user.getId(), user.getFirstName()))
                .collect(Collectors.toList());*/
        return userRepository.findAll()
                .stream().collect(Collectors.toMap(User::getId, User::getFirstName));

    }
}
