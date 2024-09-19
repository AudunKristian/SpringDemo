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

    // Getters 
    public String getQuestion() {
        return question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public List<String> getOptions() {
        return options;
    }

    // Setters (optional, if you want to create request objects manually)
    // ...
}
