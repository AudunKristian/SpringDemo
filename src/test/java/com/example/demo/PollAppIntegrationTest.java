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
    public void testFullScenario() throws Exception {
        // Step 1: Create a new user
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user1\", \"email\": \"user1@example.com\", \"password\": \"password123\" }"))
                .andExpect(status().isCreated());

        // Step 2: List all users (should show the newly created user)
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username").value("user1"));

        // Step 3: Create another user
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user2\", \"email\": \"user2@example.com\", \"password\": \"password123\" }"))
                .andExpect(status().isCreated());

        // Step 4: List all users again (should show two users)
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].username").value("user2"));

        // Step 5: User 1 creates a new poll
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

        // Step 6: List polls (should show the new poll)
        mockMvc.perform(get("/api/polls"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].question").value("What is your favorite color?"));

        // Step 7: User 2 votes on the poll
        mockMvc.perform(post("/api/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"pollId\": 1, \"userId\": 2, \"voteOptionId\": 1 }"))
                .andExpect(status().isCreated());

        // Step 8: User 2 changes his vote
        mockMvc.perform(put("/api/votes/{voteId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"voteOptionId\": 2 }"))
                .andExpect(status().isOk());

        // Step 9: List votes (should show the most recent vote for User 2)
        mockMvc.perform(get("/api/votes?pollId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].voteOptionId").value(2));

        // Step 10: Delete the one poll
        mockMvc.perform(delete("/api/polls/{pollId}", 1))
                .andExpect(status().isNoContent());

        // Step 11: List votes (should be empty)
        mockMvc.perform(get("/api/votes?pollId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]")); // Expecting an empty list
    }
}
*/
