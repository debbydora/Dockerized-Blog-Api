package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Connection extends BaseKey{
    @ManyToMany
    private List<User> usersConnection;
}
