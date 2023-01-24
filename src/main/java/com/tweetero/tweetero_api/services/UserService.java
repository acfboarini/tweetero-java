package com.tweetero.tweetero_api.services;

import java.util.List;
import java.util.Objects;

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

    public String findAvatarByUsername(String username) {
        List<User> users = this.getUsers();
        String avatar = null;
        for(User user: users) {
            if (Objects.equals(user.getUsername(), username)) {
                avatar = user.getAvatar();
                break;
            }
        }
        return avatar;
    }

    public boolean existUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            return false;
        }
        return true;
    }
}
