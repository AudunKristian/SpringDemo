package com.example.pollapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private Instant publishedAt;

    private Instant validUntil;

    private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<VoteOption> voteOptions;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Vote> votes;

    public Poll(String question, Instant publishedAt, Instant validUntil, Boolean isPublic, User creator) {
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.isPublic = isPublic;
        this.creator = creator;
    }
}
