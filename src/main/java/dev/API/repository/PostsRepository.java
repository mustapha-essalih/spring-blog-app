package dev.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.API.entity.Post;

@Repository
public interface PostsRepository extends JpaRepository<Post , Integer> {
    
}
