package com.example.demo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Comment extends BaseKey{
    private String commentBody;
    @DateTimeFormat(pattern = "yyyy:mm:dd")
    private LocalDate localDate;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
}
