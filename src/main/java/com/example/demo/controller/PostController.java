package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/newPost")
    public ResponseEntity<String> createNewPost(@RequestBody String postBody, @RequestParam String postTitle,  @PathVariable(name = "userId")Long userId){
        postService.createNewPost(postBody, postTitle, userId);
        return new ResponseEntity<>("post created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/user/{userid}/posts")
    public ResponseEntity<List<Post>> displayAllPosts(){
        List<Post> postList = postService.displayAllPosts();
        return  ResponseEntity.ok().body(postList);
    }
    @GetMapping("/displaySearchedPost/post")
    public ResponseEntity<List<Post>> displaySearchedPost(@RequestBody String keyword){
        List<Post> posts = postService.displaySearchedPost(keyword);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable(name = "userId")Long userId, @PathVariable(name = "postId")Long postId){
        postService.deletePost(userId,postId);
        return new ResponseEntity<>("post deleted successfully", HttpStatus.NO_CONTENT);
    }


}
