package dev.API.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.API.model.dto.NewPostDto;
import dev.API.model.entity.Author;
import dev.API.model.entity.Posts;
import dev.API.model.entity.RootUser;
import dev.API.repository.AuthorRepository;
import dev.API.repository.UserRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired 
    private UserRepository userRepository;

    public ResponseEntity<?> createPost(NewPostDto dto , String username){

        RootUser user = userRepository.findByUsername(username);
        
        Author author = authorRepository.findByEmail(user.getAuthor().getEmail());

       List<Posts> p  =  author.getPosts().stream().collect(Collectors.toList());  

       for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getTitle().equals(dto.getTitle())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("title aleredy exists");
            }
       }
       
        Posts posts = new Posts(dto.getTitle() , dto.getBody());

        List<Posts> newPost = new ArrayList<>();
        
        newPost.add(posts);
        author.setPosts(newPost);
        author.getPosts().size();

        user.setAuthors(author);

        posts.setAuthor(author);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("new Post created");
    }
}
