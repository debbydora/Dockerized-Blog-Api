package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.RegisterationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User addNewUser(RegisterationDto registerationDto);
    List<User> displayAllUsers();
    User loginUser(String username, String password);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    void cancelDelete(Long userId);
}
