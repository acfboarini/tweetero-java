package com.tweetero.tweetero_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetero.tweetero_api.models.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> { 
}
