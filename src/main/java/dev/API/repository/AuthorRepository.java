package dev.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.API.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author , Integer>{
    Author findByEmail(String email);
}
