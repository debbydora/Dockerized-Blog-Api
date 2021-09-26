package com.example.demo.serviceImpl;

import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Likes;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LikesRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;

    @Autowired
    public LikesServiceImpl(UserRepository userRepository, PostRepository postRepository,
                            CommentRepository commentRepository, LikesRepository likesRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likesRepository = likesRepository;
    }

    @Override
    public Boolean likeOrUnlikeAPost(Long postId, Long userId) {
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);
        LocalDateTime localDateTime = LocalDateTime.now();
        Boolean status;
        if(user.equals(null)){
            throw new UserNotFoundException("This user doesn't exist");
        } else if(post.equals(null)){
            throw new PostNotFoundException("post doesn't exist");
        }else{
            Optional<Likes> likes = likesRepository.findLikesByUserAndPost(user,post);
            if(likes.isPresent()){
                likesRepository.deleteById(likes.get().getId());
                status = false;
            } else {
                Likes likes1 = new Likes();
                likes1.setLocalDateTime(localDateTime);
                likes1.setUser(user);
                likes1.setPost(post);
                likesRepository.save(likes1);
                status = true;

            }
        }
        return status;
    }

    @Override
    public Boolean likeOrUnlikeAComment(Long commentId, Long userId) {
        User user = userRepository.getById(userId);
        Comment comment = commentRepository.getById(commentId);
        LocalDateTime localDateTime = LocalDateTime.now();
        Boolean status;
        if(user.equals(null)){
            throw  new UserNotFoundException("This user doesn't exist");
        } else if (comment==null){
            throw  new CommentNotFoundException("Comment not found");
        } else{
            Optional<Likes> likes = likesRepository.findLikesByUserAndComment(user,comment);
            if(likes.isPresent()){
                likesRepository.deleteById(likes.get().getId());
                status = false;
            }
            else{
                Likes likes1 = new Likes();
                likes1.setLocalDateTime(localDateTime);
                likes1.setUser(user);
                likes1.setComment(comment);
                likesRepository.save(likes1);
                status = true;
            }
        }
        return status;
    }
}
