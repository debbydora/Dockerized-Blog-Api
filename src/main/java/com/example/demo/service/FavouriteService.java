package com.example.demo.service;

import com.example.demo.model.Favourites;

import java.util.List;

public interface FavouriteService {
    Favourites addPostToFavourites(Long userId, Long postId);
    List<Favourites> displayAllFavouritesPost();

}
