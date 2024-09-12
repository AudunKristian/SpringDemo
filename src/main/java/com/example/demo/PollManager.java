package com.example.demo;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {
    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Poll> polls = new HashMap<>();
    private final Map<Long, VoteOption> voteOptions = new HashMap<>();
    private final Map<Long, Vote> votes = new HashMap<>();

    // methods to manage domain objects
}
