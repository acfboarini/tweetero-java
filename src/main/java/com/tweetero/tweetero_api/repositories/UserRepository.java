package com.tweetero.tweetero_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetero.tweetero_api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {    
}
