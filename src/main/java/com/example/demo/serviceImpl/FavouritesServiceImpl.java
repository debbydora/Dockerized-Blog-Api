package com.example.demo.serviceImpl;

import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Favourites;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.FavouritesRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouriteService {
    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Favourites addPostToFavourites(Long userId, Long postId) {
        LocalDateTime localDateTime = LocalDateTime.now();

        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);


        if(user == null){
           throw new UserNotFoundException("This user doesn't exist");
        } else if(post == null) {
            throw new PostNotFoundException("This post doesn't exist");
        } else{
            Optional<Favourites> favourites = favouritesRepository.findFavouritesByUser(user);
            if(!favourites.isPresent()){
                Favourites favourites1 = new Favourites();
                List<Post> posts = new ArrayList<>();
                posts.add(post);
                favourites1.setPosts(posts);
                favourites1.setUser(user);
                favourites1.setLocalDateTime(localDateTime);
                return favouritesRepository.save(favourites1);


            } else{
                List<Post> posts = favourites.get().getPosts();
                posts.add(post);
                favourites.get().setPosts(posts);
                return favouritesRepository.save(favourites.get());
            }


        }


    }

    @Override
    public List<Favourites> displayAllFavouritesPost() {
        return favouritesRepository.findAll();
    }
}
