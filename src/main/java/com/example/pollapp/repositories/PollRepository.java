package com.example.pollapp.repositories;

import com.example.pollapp.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
