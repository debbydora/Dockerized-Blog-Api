package com.example.demo.serviceImpl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.dto.RegisterationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addNewUser(RegisterationDto registerationDto) {
        User user = new User();
        user.setFirstName(registerationDto.getFirstName());
        user.setLastName(registerationDto.getLastName());
        user.setEmail(registerationDto.getEmail());
        user.setUsername(registerationDto.getUsername());
        user.setPassword(registerationDto.getPassword());
        return userRepository.save(user);

    }

    @Override
    public List<User> displayAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User loginUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username,password);

    }

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> user1 = userRepository.findById(userId);
        if(user1.isEmpty()){
            throw new UserNotFoundException("user doesn't exist");
        } else{
            user1.get().setFirstName(user.getFirstName());
            user1.get().setLastName(user.getLastName());
            user1.get().setEmail(user.getEmail());
            user1.get().setUsername(user.getUsername());
            user1.get().setPassword(user.getPassword());
            return userRepository.save(user1.get());

        }
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.getById(userId);
        if(user == null){
            throw new UserNotFoundException("user doesn't exist");
        } else {
            user.setFlag(true);
            userRepository.save(user);
        }
    }

    @Override
    public void cancelDelete(Long userId) {
        User user = userRepository.getById(userId);
        if(user.isFlag()){
            user.setFlag(false);
            userRepository.save(user);
        }

    }
}
