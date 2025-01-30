package com.example.file_validation.service;

import com.example.file_validation.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void saveUser(User user) {
        // Simulating saving the user (in a real scenario, this would interact with a database)
        System.out.println("User saved: " + user.getName());
    }
}
