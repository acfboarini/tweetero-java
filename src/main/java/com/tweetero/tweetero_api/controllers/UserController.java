package com.tweetero.tweetero_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.tweetero_api.dto.UserDTO;
import com.tweetero.tweetero_api.models.User;
import com.tweetero.tweetero_api.repositories.UserRepository;

@RestController
@RequestMapping("/sign-up")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String postUser(@RequestBody UserDTO req) {
        userRepository.save(new User(req));
        return "OK";
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}