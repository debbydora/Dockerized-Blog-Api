package com.example.demo.controller;

import com.example.demo.serviceImpl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CommentControllerTest {
    @InjectMocks
    private CommentController controller;

    @Mock
    private CommentServiceImpl commentService;


    @Test
    void shouldCreateNewCommentSuccessfully(){
        Long postId = 1L;
        Long userId = 1L;
        String commentBody = "Steven is a male specie";

        given(commentService.createNewComment(postId,commentBody,userId)).willReturn(true);
        ResponseEntity<Object> response = controller.createNewComment(userId,postId,commentBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

}