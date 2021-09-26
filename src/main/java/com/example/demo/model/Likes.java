package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Likes extends BaseKey{
    private LocalDateTime localDateTime;
    @OneToOne
    private User user;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Comment comment;
}
