package dev.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.API.dto.DeletedUserDto;
import dev.API.entity.Post;
import dev.API.entity.User;
import dev.API.repository.PostsRepository;
import dev.API.repository.UserRepository;

@Service
public class AdminService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;

    public ResponseEntity<?> deleteUser(DeletedUserDto dto){
        
        User user = userRepository.findByUsername(dto.getDeletedUser()).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        userRepository.delete(user);

        // resource has been removed but there is no message body to further describe the action or the status.
        return ResponseEntity.status(204).body("");
    }


    public ResponseEntity<?> deletePost(Integer postId) throws Exception{
        
        Post post = postsRepository.findById(postId).orElseThrow(() -> new Exception("post not found"));

        postsRepository.delete(post);

        return ResponseEntity.status(204).body("");
    }


}

