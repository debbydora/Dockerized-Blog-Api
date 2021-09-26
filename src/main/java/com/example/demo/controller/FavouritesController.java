package com.example.demo.controller;

import com.example.demo.model.Favourites;
import com.example.demo.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouritesController {
    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<Object> addPostToFavourites(@PathVariable(name = "userId") Long userId, @PathVariable(name = "postId") Long postId){
        Favourites favourites = favouriteService.addPostToFavourites(userId, postId);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(favourites!=null){
            httpHeaders.add("Information","Successful");
            return new ResponseEntity<>("The post has been successfully added", httpHeaders, HttpStatus.ACCEPTED);
        }else {
            httpHeaders.add("Information","Failed");
            return new ResponseEntity<>("The post was not added", httpHeaders, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping
    public ResponseEntity<List<Favourites>> displayAllFavouritesPost(){
        List<Favourites> favourites = favouriteService.displayAllFavouritesPost();
        return ResponseEntity.ok().body(favourites);
    }
}
