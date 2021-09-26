package com.example.demo.serviceImpl;

import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Post createNewPost(String postBody, String postTitle,Long id) {
        Post post = new Post();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        User user = userRepository.getById(id);
        post.setPostBody(postBody);
        post.setPostTitle(postTitle);
        post.setDateCreated(localDate);
        post.setTimeCreated(localTime);
        post.setLocalDateTime(localDateTime);


        Post post1= postRepository.save(post);
        user.getPosts().add(post1);
        userRepository.save(user);
        return post1;

//        post.setUser(user);
//        postRepository.save(post);
//        return post;
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userId);

        if(post == null){
            throw  new PostNotFoundException("Post doesn't exist");
        } else if (!user.getId().equals(userId)) {
            throw new UserNotFoundException("user is not authorized");
        } else {
            postRepository.delete(post);
        }

    }

    @Override
    public List<Post> displayAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> displaySearchedPost(String keyword) {
        return postRepository.findAllByPostTitleIsContaining(keyword);
    }
}