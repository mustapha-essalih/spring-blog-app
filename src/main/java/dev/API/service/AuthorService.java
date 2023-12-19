package dev.API.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.text.html.HTML.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import dev.API.dto.NewPostDto;
import dev.API.dto.UpdatePostBodyDto;
import dev.API.dto.UpdateTitle;
import dev.API.entity.Post;
import dev.API.entity.Tags;
import dev.API.entity.User;
import dev.API.repository.PostsRepository;
import dev.API.repository.TagsRepository;
import dev.API.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthorService {
    
    @Autowired
    private TagsRepository tagsRepository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private PostsRepository postsRepository;

    public ResponseEntity<?> createPost(NewPostDto dto , String username){

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        
        List<Post> p  =  user.getPosts().stream().collect(Collectors.toList());  

        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getTitle().equals(dto.getTitle())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("title aleredy exists");
            }
        }

        List<String> tags =  tagsRepository.findAll().stream().map((tag) -> tag.getTagName()).collect(Collectors.toList());
        
        if(tags.containsAll(dto.getTags()))
        {
            List<Post> newPost = new ArrayList<>();
            List<Tags> tag = new ArrayList<>();

            for (String string : dto.getTags()) {
                tag.add(new Tags(string));
            }

            Post posts = new Post(dto.getTitle() , dto.getBody() , tag);
            
            newPost.add(posts);
            
            user.setPosts(newPost);
      
            posts.setAuthor(user);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("new Post created");
        } 
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tag not found");
    }



    public ResponseEntity<?> updateTitle(UpdateTitle dto , Integer postId) throws Exception{
         
        Post post = postsRepository.findById(postId).orElseThrow(() -> new Exception("post not found"));

        if (post.getTitle().equals(dto.getTitle())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("title aleredy exists");
        }
        
        post.setTitle(dto.getTitle());

        postsRepository.save(post);
        
        return ResponseEntity.status(200).body(" title updated");
    }

    public ResponseEntity<?> updatePostBody(UpdatePostBodyDto dto ,Integer postId) throws Exception{
              
        Post post = postsRepository.findById(postId).orElseThrow(() -> new Exception("post not found"));

        post.setBody(dto.getBody());

        postsRepository.save(post);

        return ResponseEntity.status(200).body(" body of post updated");
    }
}
