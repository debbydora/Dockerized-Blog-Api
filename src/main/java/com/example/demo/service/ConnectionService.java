package com.example.demo.service;

import com.example.demo.model.Connection;
import com.example.demo.model.User;

import java.util.List;

public interface ConnectionService {
    List<User> listOfMyConnections(Long userId);
    Connection createNewConnection(Long userId, Long userId_1);

}
