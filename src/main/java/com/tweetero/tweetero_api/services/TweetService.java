package com.tweetero.tweetero_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetero.tweetero_api.dto.TweetDTO;
import com.tweetero.tweetero_api.models.Tweet;
import com.tweetero.tweetero_api.models.FormatedTweet;
import com.tweetero.tweetero_api.repositories.TweetRepository;

@Service
public class TweetService {
    
    @Autowired
    private TweetRepository tweetRepository;
    private UserService userService;
    private int size;

    public TweetService(UserService userService) {
        this.userService = userService;
        this.size = 2;
    }

    public String postTweet(TweetDTO text, String username) {
        if (userService.existUsername(username)) {
            Tweet tweet = new Tweet(text, username);
            tweetRepository.save(tweet);
            return "OK";
        }
        return "Este Username não esta registrado no sistema";
    }

    public List<FormatedTweet> getTweetsWithPagination(int page) {
        List<Tweet> tweets = tweetRepository.findAll();
        return formatTweets(tweets, page);
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

    public List<FormatedTweet> formatTweets(List<Tweet> tweets, int page) {
        List<FormatedTweet> formatedTweets = new ArrayList<>();
        int firstIndex = calculateFirstIndex(tweets.size(), page);
	    int lastIndex = calculateLastIndex(firstIndex);

        for (int i = firstIndex; i > lastIndex; i--) {
            if (tweets.get(i) != null) {
                FormatedTweet tweet = buildTweetFormat(tweets.get(i));
                formatedTweets.add(tweet);
            }
        }
        return formatedTweets;
    }

    public int calculateFirstIndex(int tweetsLength, int page) {
        return tweetsLength - 1 - this.size * (page - 1);
    }
    
    public int calculateLastIndex(int firstIndex) {
        int lastIndex = firstIndex - this.size;
        if (lastIndex < -1) {
            lastIndex = -1;
        }
        return lastIndex;
    }
    
    public FormatedTweet buildTweetFormat(Tweet tweet) {
        String avatar = userService.findAvatarByUsername(tweet.getUsername());
        return new FormatedTweet(tweet, avatar);
    }
}
