package com.example.demo;
/* 
import com.example.pollapp.entities.Poll;
import com.example.pollapp.entities.User;
import com.example.pollapp.repositories.PollRepository;
import com.example.pollapp.repositories.UserRepository;
import com.example.pollapp.services.PollManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.Instant;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class PollAppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PollRepository pollRepository;

    @MockBean
    private PollManager pollManager;

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user1\", \"email\": \"user1@example.com\", \"password\": \"password123\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateAnotherUser() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user2\", \"email\": \"user2@example.com\", \"password\": \"password123\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListAllUsersAgain() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreatePoll() throws Exception {
        User mockUser = new User("user1", "user1@example.com", "password123");
        Poll mockPoll = new Poll("What is your favorite color?",
                Instant.parse("2024-09-16T00:00:00Z"),
                Instant.parse("2024-09-30T00:00:00Z"),
                true,
                mockUser);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        Mockito.when(pollManager.createPoll(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.anyBoolean(), Mockito.anyLong(), Mockito.anyList()))
                .thenReturn(mockPoll);

        mockMvc.perform(post("/api/polls")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"question\": \"What is your favorite color?\", \"publishedAt\": \"2024-09-16T00:00:00Z\", \"validUntil\": \"2024-09-30T00:00:00Z\", \"isPublic\": true, \"creatorId\": 1, \"options\": [\"Red\", \"Blue\", \"Green\"] }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.question").value("What is your favorite color?"))
                .andExpect(jsonPath("$.creator.username").value("user1"));
    }

    @Test
    public void testListPolls() throws Exception {
        mockMvc.perform(get("/api/polls"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUserVotesOnPoll() throws Exception {
        mockMvc.perform(post("/api/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"pollId\": 1, \"userId\": 2, \"voteOptionId\": 1 }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUserChangesVote() throws Exception {
        mockMvc.perform(put("/api/votes/{voteId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"voteOptionId\": 2 }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListVotes() throws Exception {
        mockMvc.perform(get("/api/votes?pollId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeletePoll() throws Exception {
        mockMvc.perform(delete("/api/polls/{pollId}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListVotesAfterDelete() throws Exception {
        mockMvc.perform(get("/api/votes?pollId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]")); // Expecting an empty list
    }
}

*/