package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.DTO.UserDTO;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;
    private UserDTO sampleUserDTO;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setUsername("testuser");

        sampleUserDTO = new UserDTO(null, "testuser");

        session = new MockHttpSession();
    }

    @Test
    void createUser() {
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        UserDTO createdUser = userService.createUser(sampleUserDTO);

        assertNotNull(createdUser, "UserDTO should not be null");
        assertEquals(1L, createdUser.getId(), "User ID should be set to 1");
        assertEquals("testuser", createdUser.getUsername(), "Username should match");
    }

    @Test
    void login_existingUser() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(sampleUser));

        User result = userService.login("testuser", session);

        assertNotNull(result, "Returned user should not be null");
        assertEquals(1L, result.getId(), "User ID should be 1");
        assertEquals("testuser", result.getUsername(), "Username should match");
        assertEquals(1L, session.getAttribute("userId"), "Session should have userId set to 1");
    }

    @Test
    void login_newUser() {
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(2L);
            return u;
        });

        User result = userService.login("newuser", session);

        assertNotNull(result, "Returned user should not be null");
        assertEquals(2L, result.getId(), "User ID should be set to 2 for new user");
        assertEquals("newuser", result.getUsername(), "Username should match new user");
        assertEquals(2L, session.getAttribute("userId"), "Session should have userId set to 2");
    }

    @Test
    void getLoggedInUserId() {
        session.setAttribute("userId", 1L);
        Long userId = userService.getLoggedInUserId(session);
        assertEquals(1L, userId, "The retrieved userId should be 1");
    }
}
