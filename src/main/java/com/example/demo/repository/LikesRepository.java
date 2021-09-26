package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.model.Likes;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findLikesByUserAndPost(User user, Post post);
    Optional<Likes> findLikesByUserAndComment(User user, Comment comment);
}
