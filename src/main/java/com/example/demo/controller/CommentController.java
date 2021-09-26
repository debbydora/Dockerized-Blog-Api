package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/posts/{postId}/comments/newComment")
    public ResponseEntity<Object> createNewComment(@PathVariable(name = "userId")Long userId, @PathVariable(name = "postId")Long postId, @RequestBody String commentBody){
        Boolean status = commentService.createNewComment(postId,commentBody,userId);
        if(status){
            return new ResponseEntity<>("New comment created successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> displayAllCommentsOnAPost(@PathVariable(name = "postId")Long postId){
        List<Comment> commentList = commentService.displayAllCommentsOnAPost(postId);
        return ResponseEntity.ok().body(commentList);
    }

    @DeleteMapping("/user/{userId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Object> deleteComments(@PathVariable(name = "userId")Long userId, @PathVariable(name = "postId")Long postId, @PathVariable(name = "commentId")Long commentId){
        commentService.deleteComments(commentId,userId);
        return new ResponseEntity<>("deleted successfully",HttpStatus.NO_CONTENT);
    }
}
