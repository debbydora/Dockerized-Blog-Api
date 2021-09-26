package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.LikesService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LikesService likesService;

    @GetMapping
    public List<User> displayAllUsers(){
        return userService.displayAllUsers();
    }

    @PostMapping("/user/{userId}/post/{postId}/likes")
    public ResponseEntity<Object> likeOrUnlikeAPost(@PathVariable(name = "postId")Long postId,@PathVariable(name = "userId")Long userId){
        Boolean status = likesService.likeOrUnlikeAPost(postId, userId);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(status) {
            httpHeaders.add("Information","Success");
            return new ResponseEntity<>("Post successfully liked", httpHeaders, HttpStatus.ACCEPTED);
        } else{
            httpHeaders.add("Information", "Failed");
            return  new ResponseEntity<>("Post not liked", httpHeaders, HttpStatus.EXPECTATION_FAILED);

        }
    }
    @PutMapping("/updateUser/{userId}")
    public  ResponseEntity<Object> updateUser(@PathVariable(name="userId")Long userId,@RequestBody User user){
       User user1= userService.updateUser(userId,user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }


    @PostMapping("/user/{userId}/post/{postId}/comment/{commentId}")
    public ResponseEntity<Object> likeOrUnlikeAComment(@PathVariable(name = "commentId")Long commentId, @PathVariable(name = "userId")Long userId){
     Boolean status = likesService.likeOrUnlikeAComment(commentId,userId);
     HttpHeaders httpHeaders = new HttpHeaders();
     if(status){
         httpHeaders.add("Information","Success");
         return  new ResponseEntity<>("Comment successfully liked", httpHeaders, HttpStatus.ACCEPTED);
     } else {
         httpHeaders.add("Information","failed");
         return new ResponseEntity<>("comment not liked",httpHeaders,HttpStatus.EXPECTATION_FAILED);
     }
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(name = "userId")Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
     @DeleteMapping("/cancelDelete/{userId}")
    public ResponseEntity<Object> cancelDelete(@PathVariable(name = "userId") Long userId){
        userService.cancelDelete(userId);
        return new ResponseEntity<>("User deletion canceled", HttpStatus.OK);
    }
}
