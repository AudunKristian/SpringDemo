package com.example.pollapp.repositories;

import com.example.pollapp.entities.Poll;
import com.example.pollapp.entities.User;
import com.example.pollapp.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    
    // Finds all votes for a specific poll
    List<Vote> findByPoll(Poll poll);

    // Finds votes by poll and user, used for checking if the user has already voted
    List<Vote> findByPollAndUser(Poll poll, User user);

    // Finds all votes for a specific poll by poll ID
    List<Vote> findByPollId(Long pollId);
}
