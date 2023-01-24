package com.tweetero.tweetero_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetero.tweetero_api.models.User;
import com.tweetero.tweetero_api.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void postUser(User req) {
        userRepository.save(req);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
