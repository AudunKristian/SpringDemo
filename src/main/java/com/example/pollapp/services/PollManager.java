package com.example.pollapp.services;

import com.example.pollapp.entities.*;
import com.example.pollapp.repositories.*;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PollManager {

    private final PollRepository pollRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;

    public PollManager(PollRepository pollRepository, UserRepository userRepository, 
                       VoteRepository voteRepository, VoteOptionRepository voteOptionRepository) {
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    /**
     * Creates a new poll
     */
    public Poll createPoll(String question, Instant publishedAt, Instant validUntil, Boolean isPublic, Long userId, List<String> options) {
        Optional<User> creator = userRepository.findById(userId);

        if (creator.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Poll poll = new Poll(question, publishedAt, validUntil, isPublic, creator.get());
        poll = pollRepository.save(poll);

        // Create VoteOptions and associate them with the poll
        for (int i = 0; i < options.size(); i++) {
            String optionText = options.get(i);
            VoteOption voteOption = new VoteOption(optionText, i, poll);
            voteOptionRepository.save(voteOption);
        }

        return poll;
    }

    /**
     * Allows a user to vote on a poll
     */
    public Vote vote(Long pollId, Long userId, Long voteOptionId) {
        Optional<Poll> pollOptional = pollRepository.findById(pollId);
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<VoteOption> voteOptionOptional = voteOptionRepository.findById(voteOptionId);

        if (pollOptional.isEmpty()) {
            throw new RuntimeException("Poll not found");
        }

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        if (voteOptionOptional.isEmpty() || !voteOptionOptional.get().getPoll().getId().equals(pollId)) {
            throw new RuntimeException("Invalid vote option");
        }

        Poll poll = pollOptional.get();
        User user = userOptional.get();
        VoteOption voteOption = voteOptionOptional.get();

        // Check if the poll is active
        Instant now = Instant.now();
        if (now.isBefore(poll.getPublishedAt()) || now.isAfter(poll.getValidUntil())) {
            throw new RuntimeException("Poll is not active");
        }

        // For private polls, check if the user has already voted
        if (!poll.getIsPublic()) {
            List<Vote> existingVotes = voteRepository.findByPollAndUser(poll, user);
            if (!existingVotes.isEmpty()) {
                throw new RuntimeException("User has already voted in this private poll");
            }
        }

        // Save the vote
        Vote vote = new Vote(now, voteOption, user, poll);
        return voteRepository.save(vote);
    }

    /**
     * Fetches all polls
     */
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    /**
     * Fetches a specific poll by its ID
     */
    public Optional<Poll> getPollById(Long pollId) {
        return pollRepository.findById(pollId);
    }

    /**
     * Fetches all votes for a specific poll
     */
    public List<Vote> getVotesForPoll(Long pollId) {
        return voteRepository.findByPollId(pollId);
    }

    /**
     * Deletes a poll
     */
    public void deletePoll(Long pollId) {
        pollRepository.deleteById(pollId);
    }

    /**
     * Deletes a vote
     */
    public void deleteVote(Long voteId) {
        voteRepository.deleteById(voteId);
    }


}
