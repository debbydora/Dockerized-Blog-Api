package com.example.demo.repository;

import com.example.demo.model.Favourites;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    Optional<Favourites> findFavouritesByUser(User user);
    Favourites findFavouritesByPosts(Post post);
}
