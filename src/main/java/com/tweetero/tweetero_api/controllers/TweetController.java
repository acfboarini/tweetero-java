package com.tweetero.tweetero_api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.tweetero_api.dto.TweetDTO;
import com.tweetero.tweetero_api.models.Tweet;
import com.tweetero.tweetero_api.repositories.TweetRepository;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;
    private int tweets_by_page = 5;

    @PostMapping
    public String postTweet(@RequestBody TweetDTO req) {
        tweetRepository.save(new Tweet(req));
        return "OK";
    }

    @GetMapping("/{username}")
    public List<Tweet> getTweetsByUsername(@PathVariable String username) {
        List<Tweet> tweets = tweetRepository.findAll();
        return this.filterTweetsByUsername(tweets, username);
    }

    @GetMapping
    public List<Tweet> getTweets(
        @RequestParam(value = "page") int page
    ) {
        return tweetRepository.findAll();
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
