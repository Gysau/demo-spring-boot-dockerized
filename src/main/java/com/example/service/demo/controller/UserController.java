package com.example.service.demo.controller;

import com.example.service.demo.model.User;
import com.example.service.demo.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * API implementation for the CRUD operations.
 */
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${welcome.message}")
    private String valueFromFile;


    @GetMapping(value="/users")
    public Collection<User> listUser(){
        logger.info("Get all users interface called.");
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User getOne(@PathVariable(value = "id") String id){
        logger.info("Get user by id interface called. ID: [{0}]");
        return userRepository.findById(Long.valueOf(id)).orElseThrow(IllegalStateException::new);
    }

    @PostMapping(value="/signup")
    public User saveUser(@RequestBody User user){
        logger.info("Signup interface called. User: [{0}]");
        return userRepository.save(user);
    }

    @GetMapping("/message")
    public String getWelcomemessage() {
        return valueFromFile;
    }
}
