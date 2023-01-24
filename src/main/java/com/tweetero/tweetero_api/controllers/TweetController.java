package com.tweetero.tweetero_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.tweetero_api.dto.TweetDTO;
import com.tweetero.tweetero_api.models.Tweet;
import com.tweetero.tweetero_api.services.TweetService;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping
    public String postTweet(@RequestBody TweetDTO text, @RequestHeader(value = "user") String username) {
        tweetService.postTweet(text, username);
        return "OK";
    }

    @GetMapping
    public List<Tweet> getTweets(@RequestParam(value = "page") int page) {
        return tweetService.getTweetsWithPagination(page);
    }

    @GetMapping("/{username}")
    public List<Tweet> getTweetsByUsername(@PathVariable String username) {
        return tweetService.getTweetsByUsername(username);
    }
}
