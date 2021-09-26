package com.example.demo.serviceImpl;

import com.example.demo.model.Connection;
import com.example.demo.model.User;
import com.example.demo.repository.ConnectionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    private final UserRepository userRepository;

    private final ConnectionRepository connectionRepository;

     @Autowired
    public ConnectionServiceImpl(UserRepository userRepository, ConnectionRepository connectionRepository) {
        this.userRepository = userRepository;
        this.connectionRepository = connectionRepository;
    }

    @Override
    public List<User> listOfMyConnections(Long userId) {

        return userRepository.getById(userId).getConnection().getUsersConnection();
    }

    @Override
    public Connection createNewConnection(Long userId, Long userId_1) {
         User user = userRepository.getById(userId);
         User user1 = userRepository.getById(userId_1);
         Connection connection;


         if(user.getConnection() == null) {
             connection = new Connection();
             List<User> connectionsOfUsers = new ArrayList<>();
             connectionsOfUsers.add(user1);
             connection.setUsersConnection(connectionsOfUsers);
             user.setConnection(connection);
             connectionRepository.save(connection);

         } else {
             connection = user.getConnection();
             connection.getUsersConnection().add(user1);
             user.setConnection(connection);
         }
         if(user1.getConnection() == null){
             connection = new Connection();
             List<User> connectionsOfUsers = new ArrayList<>();
             connectionsOfUsers.add(user);
             connection.setUsersConnection(connectionsOfUsers);
             user1.setConnection(connection);
             connectionRepository.save(connection);

         } else{
             connection = user1.getConnection();
             connection.getUsersConnection().add(user);
             user1.setConnection(connection);
         }
         userRepository.save(user);
         userRepository.save(user1);
         return  connection;
    }



}
