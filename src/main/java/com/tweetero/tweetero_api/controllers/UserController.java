package com.tweetero.tweetero_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.tweetero_api.dto.UserDTO;
import com.tweetero.tweetero_api.models.User;
import com.tweetero.tweetero_api.services.UserService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/auth/sign-up")
    public HttpStatus postUser(@RequestBody UserDTO req) {
        String status = userService.postUser(new User(req));
        if ("Erro".equals(status)) {
            return HttpStatus.CONFLICT;
        }
        return HttpStatus.CREATED;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }
}