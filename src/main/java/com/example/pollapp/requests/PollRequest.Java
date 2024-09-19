package com.example.pollapp.requests;

import java.time.Instant;
import java.util.List;

public class PollRequest {
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private Boolean isPublic;
    private Long creatorId;
    private List<String> options;

    getQuestion() {
        return question;
    }

    setQuestion(String question) {
        this.question = question;
    }

    getPublishedAt() {
        return publishedAt;
    }

    setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    getValidUntil() {
        return validUntil;
    }

    setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    getIsPublic() {
        return isPublic;
    }

    setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    getCreatorId() {
        return creatorId;
    }

    setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    getOptions() {
        return options;
    }

    setOptions(List<String> options) {
        this.options = options;
    }

}
