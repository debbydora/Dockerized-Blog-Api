package com.example.demo.configuration;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleTask {
    private  final UserRepository userRepository;

    @Autowired
    public ScheduleTask(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedDelay = 180000)
    public void remove(){
        List<User> users = userRepository.findUserByFlag(true);
        userRepository.deleteAll(users);
    }
}
