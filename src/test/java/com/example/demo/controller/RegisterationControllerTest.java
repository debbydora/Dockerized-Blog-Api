package com.example.demo.controller;

import com.example.demo.dto.Logindto;
import com.example.demo.dto.RegisterationDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class RegisterationControllerTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private RegisterationController controller;

    @Test
    void ShouldAddNewUserSuccessfully(){
        RegisterationDto registerationDto = new RegisterationDto("Ada", "Apugo", "me@gmail.com", "bigbaby", "foodie");
        User user = new User();
        user.setFirstName(registerationDto.getFirstName());
        user.setLastName(registerationDto.getLastName());
        user.setEmail(registerationDto.getEmail());
        user.setUsername(registerationDto.getUsername());
        user.setPassword(registerationDto.getPassword());

        given(userService.addNewUser(registerationDto)).willReturn(user);

        ResponseEntity<Object> response = controller.addNewUser(registerationDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

    }
     @Test
     void shouldLoginUserSuccessfully(){
         RegisterationDto registerationDto = new RegisterationDto("Ada", "Apugo", "me@gmail.com", "bigbaby", "foodie");
         Logindto logindto = new Logindto("bigbaby","foodie");
         User user = new User();
         user.setFirstName(registerationDto.getFirstName());
         user.setLastName(registerationDto.getLastName());
         user.setEmail(registerationDto.getEmail());
         user.setUsername(registerationDto.getUsername());
         user.setPassword(registerationDto.getPassword());

         given(userService.loginUser("bigbaby","foodie")).willReturn(user);
         ResponseEntity<User> response = controller.loginUser(logindto);
         assertThat(response.getBody()).isEqualTo(user);

     }

}