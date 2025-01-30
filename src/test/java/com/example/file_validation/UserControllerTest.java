package com.example.file_validation;


import com.example.file_validation.controller.UserController;
import com.example.file_validation.model.User;
import com.example.file_validation.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;

class UserControllerTest {

    private final UserService userService = mock(UserService.class);
    private final UserController userController = new UserController(userService);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    @Test
    void createUser_shouldCallServiceWithCorrectUser() throws Exception {
        // Given
        User user = new User("John Doe", 25);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User created successfully"));

        // Then
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).saveUser(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assert capturedUser.getName().equals("John Doe");
        assert capturedUser.getAge() == 25;
    }
}
