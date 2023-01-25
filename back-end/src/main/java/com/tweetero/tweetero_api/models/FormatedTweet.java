package com.tweetero.tweetero_api.models;

public class FormatedTweet {

    private Long id;
    private String username;
    private String avatar;
    private String text;

    public FormatedTweet(Tweet tweet, String avatar) {
        this.id = tweet.getId();
        this.username = tweet.getUsername();
        this.avatar = avatar;
        this.text = tweet.getText();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getText() {
        return text;
    }
}
