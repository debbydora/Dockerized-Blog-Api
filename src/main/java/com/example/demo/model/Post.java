package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseKey{
    private String postBody;
    private String postTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate dateCreated;
    @DateTimeFormat(pattern = "HH:mm:ss" )
    private LocalTime timeCreated;
    private LocalDateTime localDateTime;
    @JsonIgnore
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
//    @ManyToOne
//    private User user;
}
