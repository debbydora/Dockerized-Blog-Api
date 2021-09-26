package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_profile")
public class User extends BaseKey {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @NotNull
    private boolean flag;

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    private Connection connection;

}
