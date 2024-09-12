package com.example.demo;

import java.time.Instant;
import java.util.List;


// Poll class
public class Poll {
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private List<VoteOption> options;
    private List<Vote> votes;

    // Constructor
    public Poll(String question, Instant publishedAt, Instant validUntil, List<VoteOption> options, List<Vote> votes) {
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.options = options;
        this.votes = votes;
    }

    // Getters and setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public List<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}

// VoteOption class
public class VoteOption {
    private String caption;
    private int presentationOrder;

    // Constructor
    public VoteOption(String caption, int presentationOrder) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    // Getters and setters
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }
}

// Vote class
public class Vote {
    private Instant publishedAt;
    private User user;
    private VoteOption voteOption;

    // Constructor
    public Vote(Instant publishedAt, User user, VoteOption voteOption) {
        this.publishedAt = publishedAt;
        this.user = user;
        this.voteOption = voteOption;
    }

    // Getters and setters
    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(VoteOption voteOption) {
        this.voteOption = voteOption;
    }
}
