package com.example.pollapp.controllers;

import com.example.pollapp.entities.Poll;
import com.example.pollapp.services.PollManager;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollManager pollManager;

    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollManager.getAllPolls();
    }

    @PostMapping
    public Poll createPoll(@RequestParam String question,
                       @RequestParam Instant publishedAt,
                       @RequestParam Instant validUntil,
                       @RequestParam Boolean isPublic,
                       @RequestParam Long creatorId,
                       @RequestParam List<String> options) {
    return pollManager.createPoll(question, publishedAt, validUntil, isPublic, creatorId, options);
}


    @GetMapping("/{id}")
    public Poll getPollById(@PathVariable Long id) {
        return pollManager.getPollById(id).orElseThrow(() -> new RuntimeException("Poll not found"));
    }

    @DeleteMapping("/{id}")
    public void deletePoll(@PathVariable Long id) {
        pollManager.deletePoll(id);
    }
}
