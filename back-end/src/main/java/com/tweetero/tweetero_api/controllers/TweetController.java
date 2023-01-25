package com.tweetero.tweetero_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.tweetero_api.dto.TweetDTO;
import com.tweetero.tweetero_api.models.FormatedTweet;
import com.tweetero.tweetero_api.models.Tweet;
import com.tweetero.tweetero_api.services.TweetService;

@RestController
@RequestMapping("/api/tweets")
@CrossOrigin(origins = "*")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping
    public ResponseEntity<Object> postTweet(@RequestBody TweetDTO text, @RequestHeader(value = "user") String username) {
        String status = tweetService.postTweet(text, username);
        if ("Erro".equals(status)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(null);
    }

    @GetMapping
    public List<FormatedTweet> getTweets(@RequestParam(value = "page") int page) {
        return tweetService.getTweetsWithPagination(page);
    }

    @GetMapping("/{username}")
    public List<Tweet> getTweetsByUsername(@PathVariable String username) {
        return tweetService.getTweetsByUsername(username);
    }
}
