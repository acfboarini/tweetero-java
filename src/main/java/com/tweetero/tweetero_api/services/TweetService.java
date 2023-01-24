package com.tweetero.tweetero_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetero.tweetero_api.dto.TweetDTO;
import com.tweetero.tweetero_api.models.Tweet;
import com.tweetero.tweetero_api.repositories.TweetRepository;

@Service
public class TweetService {
    
    @Autowired
    private TweetRepository tweetRepository;
    private int tweetsByPage = 5;

    public String postTweet(TweetDTO text, String username) {
        Tweet tweet = new Tweet(text, username);
        tweetRepository.save(tweet);
        return "OK";
    }

    public List<Tweet> getTweetsWithPagination(int page) {
        return tweetRepository.findAll();
    }

    public List<Tweet> getTweetsByUsername(String username) {
        List<Tweet> tweets = tweetRepository.findAll();
        return this.filterTweetsByUsername(tweets, username);
    }

    public List<Tweet> filterTweetsByUsername(List<Tweet> tweets, String username) {
        List<Tweet> filteredTweets = new ArrayList<>();
        tweets.forEach(tweet -> {
            if (Objects.equals(tweet.getUsername(), username)) {
                filteredTweets.add(tweet);
            }
        });
        return filteredTweets;
    }
}
