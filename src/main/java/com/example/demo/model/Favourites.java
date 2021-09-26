package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Favourites extends BaseKey{
    private LocalDateTime localDateTime;
    @JsonIgnore
    @OneToMany
    private List<Post> posts;
    @OneToOne
    @JsonIgnore
    private User user;

}
