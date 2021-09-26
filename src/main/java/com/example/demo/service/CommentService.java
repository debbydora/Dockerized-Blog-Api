package com.example.demo.service;

import com.example.demo.model.Comment;

import java.util.List;

public interface CommentService {
    Boolean createNewComment(Long postId, String commentBody, Long userId);
    List<Comment> displayAllCommentsOnAPost(Long postId);
    void deleteComments(Long commentId, Long userId);

}
