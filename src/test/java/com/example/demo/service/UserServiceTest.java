package com.example.demo.service;

import com.example.demo.dto.RegisterationDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void shouldAddNewUserSuccessfully(){
        RegisterationDto registerationDto = new RegisterationDto("Ada", "Apugo", "me@gmail.com", "bigbaby", "foodie");
        User user = new User();
        user.setFirstName(registerationDto.getFirstName());
        user.setLastName(registerationDto.getLastName());
        user.setEmail(registerationDto.getEmail());
        user.setUsername(registerationDto.getUsername());
        user.setPassword(registerationDto.getPassword());

        given(userRepository.save(user)).willReturn(user);
    //    given(userService.addNewUser(registerationDto)).willReturn(user);
        User user1 = userService.addNewUser(registerationDto);
        assertThat(user1).isEqualTo(user);



    }
    @Test
    void shouldLoginUserSuccessfully(){
        User user = new User();
        String userName = "bigbaby";
        String password = "foodie";

        given(userRepository.findUserByUsernameAndPassword(userName,password)).willReturn(user);
        User user1 = userService.loginUser(userName,password);
        assertThat(user1).isEqualTo(user);
    }

    @Test
    void ShouldReturnAllUsers(){
        List<User> users = List.of(
                new User("Maria", "Okeshipe","ma@gmail.com", "daboss","password", false,null,null),
                new User("Steve","Betterperson","st@gmail.com","gentleee","password45",false,null,null)
        );

        given(userRepository.findAll()).willReturn(users);


        List<User> users1 = userService.displayAllUsers();

        assertThat(users1).isEqualTo(users);

    }

}