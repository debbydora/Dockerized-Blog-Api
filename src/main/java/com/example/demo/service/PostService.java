package com.example.demo.service;

import com.example.demo.model.Post;

import java.util.List;

public interface PostService {
    Post createNewPost( String postBody, String postTitle, Long id);
    void deletePost(Long userId, Long postId);
    List<Post> displayAllPosts();
    List<Post> displaySearchedPost(String keyword);
}
