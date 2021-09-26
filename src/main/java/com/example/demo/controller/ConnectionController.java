package com.example.demo.controller;

import com.example.demo.model.Connection;
import com.example.demo.model.User;
import com.example.demo.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connection")
public class ConnectionController {
    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }
     @GetMapping("/listOfMyConnections/{userId}")
    public ResponseEntity<List<User>> listOfMyConnections(@PathVariable(name = "userId")Long userId){
        List<User> connections = connectionService.listOfMyConnections(userId);
        return new ResponseEntity<>(connections, HttpStatus.OK);
    }

    @PostMapping("/createNewConnection/{userId}/{userId_1}")
    public ResponseEntity<Connection> createNewConnection(@PathVariable(name = "userId") Long userId, @PathVariable(name = "userId_1")Long userId_1){
        Connection connection = connectionService.createNewConnection(userId,userId_1);
        return  new ResponseEntity<>(connection, HttpStatus.OK);
    }

}
