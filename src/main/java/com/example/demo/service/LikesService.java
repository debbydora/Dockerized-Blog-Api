package com.example.demo.service;

public interface LikesService {
    Boolean likeOrUnlikeAPost(Long postId, Long userId);
    Boolean likeOrUnlikeAComment(Long commentId, Long userId);
}
