package com.tweetero.tweetero_api.models;

import com.tweetero.tweetero_api.dto.TweetDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Tweet { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 500, nullable = false)
    private String text;

    public Tweet(TweetDTO data) {
        this.username = data.username();
        this.text = data.text();
    }
}
