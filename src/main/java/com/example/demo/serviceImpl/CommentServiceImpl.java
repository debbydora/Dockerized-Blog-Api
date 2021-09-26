package com.example.demo.serviceImpl;

import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public Boolean createNewComment(Long postId, String commentBody, Long userId) {
       Boolean status = false;
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        Comment comment = new Comment();
        comment.setCommentBody(commentBody);
        comment.setLocalDate(localDate);
        comment.setLocalTime(localTime);
        comment.setLocalDateTime(localDateTime);

        comment.setUser(user);
comment.setPost(post);
        Comment comment1 = commentRepository.save(comment);
//        List<Comment> commentList = post.getComments();
//        commentList.add(comment1);
//        postRepository.save(post);

        if(comment1 != null){
            status = true;
        }
        else{
            status = false;
        }
        return status;

    }

    @Override
    public List<Comment> displayAllCommentsOnAPost(Long postId) {
        Optional<Post>posts = postRepository.findById(postId);
        if(posts.isPresent()){
            List<Comment>comments= commentRepository.findCommentsByPost(posts.get());
            return comments;

        }
        else {
            throw new PostNotFoundException("post doesn't exist");
        }
    }

    @Override
    public void deleteComments(Long commentId, Long userId) {
        Comment comment = commentRepository.getById(commentId);
        User user = comment.getUser();

        if(comment.equals(null)){
            throw new CommentNotFoundException("comment doesn't exist");
        } else if(!user.getId().equals(userId)){
            throw new UserNotFoundException("user is not authorized");
        } else {
            commentRepository.delete(comment);
        }
    }
}
