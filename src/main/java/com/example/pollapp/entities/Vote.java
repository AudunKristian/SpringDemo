package com.example.pollapp.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant publishedAt;

    @ManyToOne
    @JoinColumn(name = "vote_option_id")
    private VoteOption voteOption;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    public Vote(Instant publishedAt, VoteOption voteOption, User user, Poll poll) {
        this.publishedAt = publishedAt;
        this.voteOption = voteOption;
        this.user = user;
        this.poll = poll;
    }
}
