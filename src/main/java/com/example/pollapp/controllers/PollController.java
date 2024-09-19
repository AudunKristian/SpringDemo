package com.example.pollapp.controllers;

import com.example.pollapp.entities.Poll;
import com.example.pollapp.services.PollManager;
import org.springframework.web.bind.annotation.*;
import com.example.pollapp.requests.PollRequest;

import java.time.Instant;
import java.util.List;


@CrossOrigin(origins = "*")
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
    public Poll createPoll(@RequestBody PollRequest pollRequest) {
        return pollManager.createPoll(pollRequest.getQuestion(), pollRequest.getPublishedAt(),
                                  pollRequest.getValidUntil(), pollRequest.getIsPublic(),
                                  pollRequest.getCreatorId(), pollRequest.getOptions());
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
